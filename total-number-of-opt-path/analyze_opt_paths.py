#!/usr/bin/env python3
"""Manually report Total # of Opt. Path, with one PDF per system.

Run with the configured Python interpreter:
    python analyze_opt_paths.py                 # all five systems
    python analyze_opt_paths.py MopFuzzer       # one system
    python analyze_opt_paths.py all --seed-baseline 8327

The default data root is this script's directory. EvoFuzz and EvoFuzz-Rand
use the last 'Total paths recorded:' in run.log; Artemis uses the last
'Total unique path num:' in out/out.log, when all recorded tests fit the window.
Longer runs are capped using their timestamped per-test new-path counts.
MopFuzzer and EvoFuzz-Coarse count
the union of normalized paths in out/*_route_data.log, but only when the
matching mutants/<session>/<iteration>/route_data.log starts with SUCCESS.
FAILED, TIMEOUT, missing/unknown statuses, and incomplete headers are excluded.
Keep mutants/ alongside out/: raw traces alone do not record execution status.

Only PDFs are written. Inputs are never changed; no fuzzers are started.
The window is at most 12 hours from the earliest recorded test START_NS,
including only tests whose END_NS is at or before the cutoff. This is the
recorded test window, not launcher startup time. --hours may shorten it.
No seed count is added to the totals. The optional dashed
seed baseline is a visual reference only; set it to the measured seed count.
Requires Python 3.10+ and matplotlib; see the root requirements.txt.
"""

from __future__ import annotations

import argparse
import ast
from collections import Counter
from dataclasses import dataclass, field
import math
from pathlib import Path, PurePosixPath
import re
import shlex
import sys
from typing import Iterable


SYSTEMS = ("EvoFuzz", "EvoFuzz-Rand", "EvoFuzz-Coarse", "Artemis", "MopFuzzer")
COLORS = dict(zip(SYSTEMS, ("#12324d", "#2a5076", "#55799d", "#8da6bd", "#e8ecef")))
LABELS = {"EvoFuzz-Rand": r"EvoFuzz$_{rand}$", "EvoFuzz-Coarse": r"EvoFuzz$_{coarse}$"}
TOTAL_PATTERNS = {
    "EvoFuzz": re.compile(r"\bTotal paths recorded:\s*(\d+)\s*$"),
    "EvoFuzz-Rand": re.compile(r"\bTotal paths recorded:\s*(\d+)\s*$"),
    "Artemis": re.compile(r"\bTotal unique path num:\s*(\d+)\s*$"),
}
OptPath = tuple[str, str, tuple[int, ...]]
NS_PER_HOUR = 3_600_000_000_000
RECORD_PAIR = re.compile(r"(\([^()]*\))\s+(\([^()]*\))")


@dataclass
class Result:
    system: str
    total: int
    source: Path
    line_number: int | None = None
    traces: Counter = field(default_factory=Counter)
    start_ns: int | None = None
    observed_hours: float = 0.0


def window_ns(hours: float) -> int:
    if not math.isfinite(hours) or not 0 < hours <= 12:
        raise ValueError("--hours must be greater than 0 and at most 12")
    return int(hours * NS_PER_HOUR)


def recorded_total(system: str, repo: Path, hours: float) -> Result:
    """Use the final total only when its timestamped records all fit the window."""
    duration = window_ns(hours)
    if system == "Artemis":
        candidates = list((repo / "out").glob("path_rec_mopfuzzer_*.txt"))
        if len(candidates) != 1:
            raise ValueError(f"expected one out/path_rec_mopfuzzer_*.txt under {repo} to verify the time window")
        recording = candidates[0]
    else:
        recording = repo / "out/path_rec.txt"
    rows = []
    with recording.open(encoding="utf-8") as stream:
        for line_number, line in enumerate(stream, 1):
            if not line.strip():
                continue
            match = RECORD_PAIR.fullmatch(line.strip())
            try:
                if match is None:
                    raise ValueError("expected timestamp and count tuples")
                times, counts = (ast.literal_eval(part) for part in match.groups())
                if (not isinstance(times, tuple) or not isinstance(counts, tuple)
                        or len(times) != 2 or len(counts) != (2 if system == "Artemis" else 3)
                        or any(type(value) is not int for value in times + counts)
                        or times[0] <= 0 or times[1] < times[0] or counts[0] < 0):
                    raise ValueError("invalid timestamps or new-path count")
            except (ValueError, SyntaxError) as exc:
                raise ValueError(f"malformed record at {recording}:{line_number}: {line.strip()}") from exc
            rows.append((times[0], times[1], counts[0]))
    if not rows:
        raise ValueError(f"no timestamped path records in {recording}")
    start = min(row[0] for row in rows)
    cutoff = start + duration
    selected = [row for row in rows if row[1] <= cutoff]
    total = sum(row[2] for row in selected)
    if len(selected) == len(rows):
        result = last_printed_total(system, repo)
        if result.total != total:
            raise ValueError(f"printed total {result.total} disagrees with {recording} ({total}); check for mixed/incomplete runs")
    else:
        result = Result(system, total, recording)
    result.traces.update(found=len(rows), in_window=len(selected), outside_window=len(rows) - len(selected))
    result.start_ns = start
    result.observed_hours = (max(row[1] for row in rows) - start) / NS_PER_HOUR
    return result


def last_printed_total(system: str, repo: Path) -> Result:
    log = repo / ("out/out.log" if system == "Artemis" else "run.log")
    result = None
    with log.open(encoding="utf-8", errors="replace") as stream:
        for line_number, line in enumerate(stream, 1):
            match = TOTAL_PATTERNS[system].search(line)
            if match:
                result = Result(system, int(match.group(1)), log, line_number)
    if result is None:
        raise ValueError(f"no final path total in {log}; run after the system prints its summary")
    return result


def read_header(path: Path) -> dict[str, str]:
    header = {}
    with path.open(encoding="utf-8", errors="replace") as stream:
        for line in stream:
            if line.startswith("File:::: "):
                break
            key, separator, value = line.strip().partition(":::: ")
            if separator and key in ("START_NS", "END_NS", "CMD_LINE"):
                if key in header:
                    raise ValueError(f"duplicate {key}")
                header[key] = value
    try:
        start, end = int(header["START_NS"]), int(header["END_NS"])
        if start <= 0 or end < start or not header["CMD_LINE"]:
            raise ValueError("incomplete trace header")
    except (KeyError, ValueError) as exc:
        raise ValueError("missing or invalid timestamps/command line") from exc
    return header


def status_file(command: str, repo: Path) -> Path:
    """Locate the current mutant, not the iteration-0 classpath fallback."""
    arguments = shlex.split(command)
    classpath = None
    for index, argument in enumerate(arguments):
        if argument in ("-cp", "-classpath", "--class-path") and index + 1 < len(arguments):
            classpath = arguments[index + 1]
            break
        if argument.startswith("--class-path="):
            classpath = argument.partition("=")[2]
            break
    if classpath is None:
        raise ValueError("no classpath in CMD_LINE")
    parts = PurePosixPath(classpath.split(":", 1)[0]).parts
    if "mutants" not in parts or ".." in parts:
        raise ValueError("classpath does not identify a mutant")
    # Rebase absolute recorded paths too, so a copied experiment remains usable.
    relative = parts[parts.index("mutants"):]
    if len(relative) != 3 or not relative[-1].isdigit():
        raise ValueError("expected mutants/<session>/<iteration> as the first classpath entry")
    result = repo.joinpath(*relative, "route_data.log").resolve()
    if not result.is_relative_to((repo / "mutants").resolve()):
        raise ValueError("status path is outside the mutants directory")
    return result


def normalize_path(line: str) -> tuple[int, ...]:
    """Use the monotonically increasing line-number filter in MopBench/main.py."""
    numbers = []
    previous = -2
    for token in line.split("->"):
        try:
            number = int(token.strip())
        except ValueError:
            continue
        if number > previous:
            numbers.append(number)
            previous = number
    return tuple(numbers)


def iter_paths(stream: Iterable[str]) -> Iterable[OptPath]:
    source = function = ""
    for raw in stream:
        line = raw.strip()
        if line.startswith("File:::: "):
            parts = PurePosixPath(line[len("File:::: "):]).parts
            opto_indices = [index for index, part in enumerate(parts) if part == "opto"]
            source = "/".join(parts[opto_indices[-1]:]) if opto_indices else ""
            function = ""
        elif line.startswith("Function:::: "):
            function = line[len("Function:::: "):] if source else ""
        elif line and "::::" not in line and source and function:
            path = normalize_path(line)
            if path:
                yield source, function, path


def count_successful_paths(system: str, repo: Path, hours: float = 12) -> Result:
    duration = window_ns(hours)
    data_dir = repo / "out"
    if not data_dir.is_dir() or not (repo / "mutants").is_dir():
        raise ValueError(f"both out/ and mutants/ are required under {repo}")
    result = Result(system, 0, data_dir)
    unique: set[OptPath] = set()
    headers = []
    for trace in data_dir.rglob("*_route_data.log"):
        result.traces["found"] += 1
        try:
            header = read_header(trace)
        except ValueError:
            result.traces["incomplete_header"] += 1
            continue
        headers.append((trace, header))
    if not result.traces["found"]:
        raise ValueError(f"no *_route_data.log files in {data_dir}")
    if not headers:
        raise ValueError(f"no complete trace headers in {data_dir}")
    # Establish the origin before filtering failures, so failures cannot extend the window.
    start = min(int(header["START_NS"]) for _, header in headers)
    cutoff = start + duration
    result.start_ns = start
    result.observed_hours = (max(int(header["END_NS"]) for _, header in headers) - start) / NS_PER_HOUR
    for trace, header in headers:
        if int(header["END_NS"]) > cutoff:
            result.traces["outside_window"] += 1
            continue
        try:
            status_path = status_file(header["CMD_LINE"], repo)
        except ValueError:
            result.traces["unmatched_command"] += 1
            continue
        try:
            with status_path.open(encoding="utf-8-sig", errors="replace") as stream:
                status = stream.readline().strip()
        except FileNotFoundError:
            result.traces["missing_status"] += 1
            continue
        if status != "SUCCESS":
            key = status.lower() if status in ("FAILED", "TIMEOUT") else "unknown_status"
            result.traces[key] += 1
            continue
        with trace.open(encoding="utf-8", errors="replace") as stream:
            unique.update(iter_paths(stream))
        result.traces["success"] += 1
    known = sum(result.traces[key] for key in ("success", "failed", "timeout"))
    if not known and result.traces["outside_window"] != len(headers):
        raise ValueError(f"no traces have a verifiable execution status under {repo}: {dict(result.traces)}")
    result.total = len(unique)
    return result


def analyze(system: str, root: Path, hours: float = 12) -> Result:
    repo = root / system
    if system in TOTAL_PATTERNS:
        return recorded_total(system, repo, hours)
    return count_successful_paths(system, repo, hours)


def plot(result: Result, output: Path, ymax: int, baseline: int | None) -> None:
    import matplotlib
    matplotlib.use("Agg")
    from matplotlib import pyplot as plt

    with plt.rc_context({"font.family": "serif", "mathtext.fontset": "dejavuserif",
                         "mathtext.default": "regular", "pdf.fonttype": 42}):
        fig, ax = plt.subplots(figsize=(4.8, 4.8), constrained_layout=True)
        try:
            ax.bar([0], [result.total], width=0.5, color=COLORS[result.system],
                   edgecolor="#2a5076", linewidth=1.6, zorder=3)
            ax.annotate(str(result.total), (0, result.total), xytext=(0, 7),
                        textcoords="offset points", ha="center", va="bottom",
                        fontsize=15, fontweight="bold")
            ax.set_xticks([0])
            ax.set_xticklabels([LABELS.get(result.system, result.system)],
                               fontsize=18, fontweight="bold")
            ax.set_ylabel("Total # of Opt. Path", fontsize=18, fontweight="bold", labelpad=10)
            ax.set_xlim(-0.65, 0.65)
            ax.set_ylim(0, ymax)
            ticks = list(range(0, ymax + 1, max(5000, math.ceil(ymax / 25000) * 5000)))
            if baseline is not None:
                ticks = [tick for tick in ticks if tick == 0 or abs(tick - baseline) > ymax * 0.045]
                ticks = sorted(set(ticks + [baseline]))
                ax.axhline(baseline, color="#454444", linestyle="--", linewidth=2, zorder=2)
            ax.set_yticks(ticks)
            ax.tick_params(axis="x", length=0, pad=10)
            ax.tick_params(axis="y", labelsize=13)
            for label in ax.get_yticklabels():
                label.set_fontweight("bold")
            for spine in ax.spines.values():
                spine.set_linewidth(1.4)
                spine.set_color("#111111")
            ax.grid(axis="y", color="#9a9a9a", alpha=0.45, linewidth=0.8, zorder=0)
            ax.set_axisbelow(True)
            fig.savefig(output, format="pdf", bbox_inches="tight", facecolor="white")
        finally:
            plt.close(fig)


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("system", nargs="?", default="all", choices=("all",) + SYSTEMS)
    parser.add_argument("--root", type=Path, default=Path(__file__).resolve().parent,
                        help="Directory containing the five system repositories")
    parser.add_argument("--output-dir", type=Path,
                        help="Write <system>-total-opt-paths.pdf here instead of <system>/total_opt_paths.pdf")
    parser.add_argument("--seed-baseline", type=int,
                        help="Optional measured initial-seed path count (visual reference, not added to total)")
    parser.add_argument("--hours", type=float, default=12,
                        help="Maximum recorded test interval in hours: 0 < hours <= 12 (default: 12)")
    args = parser.parse_args()
    try:
        window_ns(args.hours)
    except ValueError as exc:
        parser.error(str(exc))
    if args.seed_baseline is not None and args.seed_baseline < 0:
        parser.error("--seed-baseline must be nonnegative")
    results = []
    failed = False
    for system in SYSTEMS if args.system == "all" else (args.system,):
        try:
            result = analyze(system, args.root, args.hours)
            results.append(result)
            location = f"{result.source}:{result.line_number}" if result.line_number else str(result.source)
            print(f"{system}: {result.total} paths ({location})")
            if result.traces:
                print("  Records/traces: " + ", ".join(f"{key}={value}" for key, value in sorted(result.traces.items())))
            print(f"  Window: <= {args.hours:g} h from START_NS={result.start_ns}; input spans {result.observed_hours:.6f} h")
        except (OSError, ValueError) as exc:
            print(f"error: {system}: {exc}", file=sys.stderr)
            failed = True
    if not results:
        return 1
    ymax = max(24000, math.ceil(max([item.total for item in results] + [args.seed_baseline or 0]) * 1.18 / 5000) * 5000)
    try:
        if args.output_dir:
            args.output_dir.mkdir(parents=True, exist_ok=True)
        for result in results:
            output = (args.output_dir / f"{result.system}-total-opt-paths.pdf" if args.output_dir
                      else args.root / result.system / "total_opt_paths.pdf")
            plot(result, output, ymax, args.seed_baseline)
            print(f"Saved {output}")
    except ImportError as exc:
        print(f"error: matplotlib is required; use the allfuzzer environment ({exc})", file=sys.stderr)
        return 1
    except OSError as exc:
        print(f"error: {exc}", file=sys.stderr)
        return 1
    return int(failed)


if __name__ == "__main__":
    sys.exit(main())
