#!/usr/bin/env python3
"""Manual Figure 7-style set-distance PDFs for the AE experiments.

Usage (from the deployed set-distance directory):
    ./draw.sh                         # all five systems
    ./MopFuzzer/draw.sh                # one system
    ./EvoFuzz/draw.sh --hours 6        # shorten the window (never >12 hours)

Distance is |paths(mutant) ^ paths(original seed)|, once per recorded test,
not cumulative path coverage, a final printed path count, or a reward score.
Retain each complete path sequence and
deduplicate (source, function, path) triples. Normalize source filenames to
opto/... so build-directory prefixes do not introduce artificial differences.
Do not discard backward/repeated line numbers, clamp distances, or scale them.

EvoFuzz/Rand use out/**/route_data.log and seeds/allfuzzer-100/TestNNNN.score.
MopFuzzer/Coarse use out/*_route_data.log and
benchmarks/JavaFuzzer/allfuzzer-100/TestNNNN.score. Only SUCCESS in the current
mutant's mutants/<session>/<iteration>/route_data.log is accepted for these
two systems. Keep mutants/ alongside out/. Never use iteration 0's status.
These baselines are the original seeds, not subsequently promoted mutants.
--seed-dir can select a different original-seed trace directory for one system.
It must contain matching TestNNNN.score (raw) or TestNNNN.score.json files.

Artemis pairs out/out-log traces using CMD_LINE's out/jaf/<reference-id> and
out/jaf/<reference-id>/mutants/<mutation-id> classpaths. Only mutants become
samples; reference executions supply the baselines. No JVMs are re-executed.

The window starts at the earliest recorded test START_NS, before status
filtering (excluding historical .score baselines). Only complete tests with
END_NS <= start + hours are used. Run manually after fuzzing has stopped.
Missing baselines or an empty sample set cause an error, not an invented plot.
Only PDFs are written. No raw data, launchers, or configuration files change.
Requires Python 3.10+ and matplotlib; see the root requirements.txt.
"""

from __future__ import annotations

import argparse
from collections import Counter, defaultdict
from dataclasses import dataclass, field
from functools import lru_cache
import json
import math
from pathlib import Path, PurePosixPath
import posixpath
import re
import shlex
from statistics import median
import sys


SYSTEMS = ("EvoFuzz", "EvoFuzz-Rand", "EvoFuzz-Coarse", "Artemis", "MopFuzzer")
FILTERED = ("MopFuzzer", "EvoFuzz-Coarse")
COLORS = dict(zip(SYSTEMS, ("#12324d", "#2a5076", "#55799d", "#8da6bd", "#e8ecef")))
LABELS = {"EvoFuzz-Rand": r"EvoFuzz$_{rand}$", "EvoFuzz-Coarse": r"EvoFuzz$_{coarse}$"}
NS_PER_HOUR = 3_600_000_000_000
PATH_PATTERN = re.compile(r"-?\d+(?:\s*->\s*-?\d+)*")
OptPath = tuple[str, str, tuple[int, ...]]


@dataclass(frozen=True)
class Trace:
    path: Path
    start: int
    end: int | None
    command: str


@dataclass
class Result:
    system: str
    distances: list[int] = field(default_factory=list)
    counts: Counter = field(default_factory=Counter)
    observed_hours: float = 0.0


def window_ns(hours: float) -> int:
    if not math.isfinite(hours) or not 0 < hours <= 12:
        raise ValueError("--hours must be greater than 0 and at most 12")
    return int(hours * NS_PER_HOUR)


def read_header(path: Path) -> Trace:
    header = {}
    with path.open(encoding="utf-8", errors="replace") as stream:
        for line in stream:
            if line.startswith("File:::: "):
                break
            key, sep, value = line.strip().partition(":::: ")
            if sep and key in ("START_NS", "END_NS", "CMD_LINE"):
                if key in header:
                    raise ValueError(f"duplicate {key} in {path}")
                header[key] = value
    try:
        start = int(header["START_NS"])
        end = int(header["END_NS"]) if "END_NS" in header else None
        if start <= 0 or (end is not None and end < start):
            raise ValueError("invalid timestamps")
    except (KeyError, ValueError) as exc:
        raise ValueError(f"missing or invalid timestamps in {path}") from exc
    return Trace(path, start, end, header.get("CMD_LINE", ""))


def source_name(name: str) -> str:
    parts = PurePosixPath(name).parts
    indices = [i for i, part in enumerate(parts) if part == "opto"]
    return "/".join(parts[indices[-1]:]) if indices else ""


def path_entry(source: str, function: str, line: str) -> OptPath:
    if not PATH_PATTERN.fullmatch(line.strip()):
        raise ValueError(f"invalid optimization path: {line[:120]!r}")
    return source, function, tuple(int(token) for token in line.split("->"))


def load_paths(path: Path) -> frozenset[OptPath]:
    """Keep full path sequences, as in the reference set-distance calculator."""
    entries = set()
    with path.open(encoding="utf-8") as stream:
        first = stream.readline()
        stream.seek(0)
        if not first.strip():
            raise ValueError(f"empty or invalid seed/trace file: {path}")
        if first.lstrip().startswith("{"):
            data = json.load(stream)
            for source, functions in data.items():
                source = source_name(source)
                if not source:
                    continue
                for function, paths in functions.items():
                    for line in paths:
                        entries.add(path_entry(source, function, line))
        else:
            source = function = ""
            for raw in stream:
                line = raw.strip()
                if line.startswith("File:::: "):
                    source = source_name(line[len("File:::: "):])
                    function = ""
                elif line.startswith("Function:::: "):
                    function = line[len("Function:::: "):]
                elif line and "::::" not in line and source and function:
                    entries.add(path_entry(source, function, line))
    return frozenset(entries)


def command_info(command: str) -> tuple[str, str]:
    arguments = shlex.split(command)
    classpath = None
    for index, argument in enumerate(arguments):
        if argument in ("-cp", "-classpath", "--class-path") and index + 1 < len(arguments):
            classpath = arguments[index + 1]
            break
        if argument.startswith("--class-path="):
            classpath = argument.partition("=")[2]
            break
    if not classpath:
        raise ValueError("no classpath in CMD_LINE")
    main_class = PurePosixPath(arguments[-1]).name.removesuffix(".class")
    if not re.fullmatch(r"(?:\w+\.)*Test\d+", main_class):
        raise ValueError(f"cannot identify TestNNNN main class: {arguments[-1]}")
    return classpath.split(":", 1)[0], main_class.rsplit(".", 1)[-1]


def status_file(classpath: str, repo: Path) -> Path:
    parts = PurePosixPath(classpath).parts
    if "mutants" not in parts or ".." in parts:
        raise ValueError("classpath does not identify a mutant")
    relative = parts[parts.index("mutants"):]
    if len(relative) != 3 or not relative[-1].isdigit():
        raise ValueError("expected mutants/<session>/<iteration> in first classpath entry")
    result = repo.joinpath(*relative, "route_data.log").resolve()
    if not result.is_relative_to((repo / "mutants").resolve()):
        raise ValueError("status path is outside mutants/")
    return result


def artemis_identity(classpath: str, seed: str) -> tuple[tuple[str, str], bool]:
    parts = PurePosixPath(posixpath.normpath(classpath)).parts
    for i in range(len(parts) - 2):
        if parts[i:i + 2] != ("out", "jaf"):
            continue
        tail = parts[i + 2:]
        if tail[0].isdigit() and len(tail) == 1:
            return (tail[0], seed), False
        if (len(tail) == 3 and tail[0].isdigit()
                and tail[1] == "mutants" and tail[2].isdigit()):
            return (tail[0], seed), True
    raise ValueError("expected Artemis out/jaf/<id>[/mutants/<id>] classpath")


def find_seed(seed_dir: Path, seed: str) -> Path:
    candidates = [seed_dir / (seed + suffix) for suffix in (".score", ".score.json")]
    # Prefer original raw data when an old converted JSON copy is also present.
    for candidate in candidates:
        if candidate.is_file():
            return candidate
    raise ValueError(f"missing original seed trace {seed_dir / (seed + '.score')}")


def analyze(system: str, repo: Path, hours: float = 12, seed_dir: Path | None = None) -> Result:
    duration = window_ns(hours)
    result = Result(system)
    data_dir = repo / ("out/out-log" if system == "Artemis" else "out")
    pattern = "route_data.log" if system in ("EvoFuzz", "EvoFuzz-Rand") else "*_route_data.log"
    traces = []
    for path in sorted(data_dir.rglob(pattern)):
        result.counts["found"] += 1
        try:
            traces.append(read_header(path))
        except ValueError:
            result.counts["invalid_header"] += 1
    if not traces:
        raise ValueError(f"no timestamped traces in {data_dir}: {dict(result.counts)}")
    start = min(trace.start for trace in traces)
    cutoff = start + duration
    result.observed_hours = (max(trace.end or trace.start for trace in traces) - start) / NS_PER_HOUR
    selected = []
    for trace in traces:
        if trace.end is None or not trace.command:
            result.counts["incomplete"] += 1
        elif trace.end > cutoff:
            result.counts["outside_window"] += 1
        else:
            selected.append(trace)

    if seed_dir is None:
        relative = "benchmarks/JavaFuzzer/allfuzzer-100" if system in FILTERED else "seeds/allfuzzer-100"
        seed_dir = repo / relative
    references = defaultdict(list)
    mutants = []
    for trace in selected:
        try:
            classpath, seed = command_info(trace.command)
            if system == "Artemis":
                identity, is_mutant = artemis_identity(classpath, seed)
                if not is_mutant:
                    references[identity].append(trace)
                    result.counts["reference"] += 1
                    continue
                mutants.append((trace, identity))
            else:
                if system in FILTERED:
                    status_path = status_file(classpath, repo)
                    try:
                        with status_path.open(encoding="utf-8-sig", errors="replace") as stream:
                            status = stream.readline().strip()
                    except FileNotFoundError:
                        result.counts["missing_status"] += 1
                        continue
                    if status != "SUCCESS":
                        key = status.lower() if status in ("FAILED", "TIMEOUT") else "unknown_status"
                        result.counts[key] += 1
                        continue
                mutants.append((trace, seed))
        except ValueError as exc:
            raise ValueError(f"cannot pair {trace.path}: {exc}") from exc

    cached_seed_paths = lru_cache(maxsize=32)(load_paths)
    for trace, identity in mutants:
        if system == "Artemis":
            candidates = [ref for ref in references[identity] if ref.end <= trace.start]
            if len(candidates) != 1:
                raise ValueError(f"expected one completed reference for {trace.path}, found {len(candidates)}")
            baseline = candidates[0].path
        else:
            baseline = find_seed(seed_dir, identity)
        result.distances.append(len(cached_seed_paths(baseline) ^ load_paths(trace.path)))
        result.counts["samples"] += 1
    if not result.distances:
        hint = "; Artemis needs both reference and mutant traces; check out/out.log for mutation errors" if system == "Artemis" else ""
        raise ValueError(f"no eligible mutant/seed pairs: {dict(result.counts)}{hint}")
    return result


def draw_pdf(result: Result, output: Path, hours: float) -> None:
    import matplotlib
    matplotlib.use("Agg")
    import matplotlib.pyplot as plt
    from matplotlib.ticker import MaxNLocator

    plt.rcParams.update({"font.family": "serif", "font.size": 12, "pdf.fonttype": 42})
    fig, ax = plt.subplots(figsize=(4.6, 4.6), layout="constrained")
    edge = "#2a5076"
    box = ax.boxplot(
        [result.distances], widths=0.38, notch=True, patch_artist=True, showfliers=False,
        boxprops={"facecolor": COLORS[result.system], "edgecolor": edge, "linewidth": 1.3},
        medianprops={"color": "#111111", "linewidth": 1.6},
        whiskerprops={"color": edge, "linewidth": 1.3},
        capprops={"color": edge, "linewidth": 1.3},
    )
    middle = median(result.distances)
    ax.text(1.23, middle, f"{middle:g}", ha="left", va="center", fontsize=12)
    ax.set_xticks([1], [LABELS.get(result.system, result.system)], fontsize=15, fontweight="bold")
    ax.set_ylabel("Set Distance", fontsize=16, fontweight="bold")
    ax.set_xlim(0.55, 1.65)
    # Use the displayed box/whiskers for limits, preserving all raw values in statistics.
    visible_top = max(max(line.get_ydata()) for line in box["whiskers"])
    visible_top = max(visible_top, box["boxes"][0].get_path().vertices[:, 1].max())
    ax.set_ylim(0, max(1, visible_top * 1.12))
    ax.yaxis.set_major_locator(MaxNLocator(nbins=6, integer=True))
    ax.set_title(f"Limit: {hours:g} h | n = {len(result.distances):,}", fontsize=11, pad=12)
    ax.grid(axis="y", color="#9a9a9a", alpha=0.45, linewidth=0.8)
    ax.set_axisbelow(True)
    ax.tick_params(axis="x", length=0, pad=10)
    for spine in ax.spines.values():
        spine.set_linewidth(1.2)
    fig.savefig(output, format="pdf", bbox_inches="tight", facecolor="white")
    plt.close(fig)


def main(argv: list[str] | None = None) -> int:
    parser = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("system", choices=("all",) + SYSTEMS, nargs="?", default="all")
    parser.add_argument("--root", type=Path, default=Path(__file__).resolve().parent)
    parser.add_argument("--hours", type=float, default=12)
    parser.add_argument("--seed-dir", type=Path, help="original seed traces for one non-Artemis system")
    parser.add_argument("--output-dir", type=Path, help="optional directory for <system>_set_distance.pdf")
    args = parser.parse_args(argv)
    try:
        window_ns(args.hours)
    except ValueError as exc:
        parser.error(str(exc))
    if args.seed_dir and args.system in ("all", "Artemis"):
        parser.error("--seed-dir requires one non-Artemis system")
    systems = SYSTEMS if args.system == "all" else (args.system,)
    failures = 0
    for system in systems:
        repo = args.root.resolve() / system
        try:
            result = analyze(system, repo, args.hours, args.seed_dir)
            output = (args.output_dir / f"{system}_set_distance.pdf" if args.output_dir
                      else repo / "set_distance.pdf")
            output.parent.mkdir(parents=True, exist_ok=True)
            draw_pdf(result, output, args.hours)
            print(f"{system}: n={len(result.distances)}, median={median(result.distances):g}, "
                  f"observed={result.observed_hours:.3f} h, limit={args.hours:g} h; "
                  f"{dict(result.counts)} -> {output}", flush=True)
        except (OSError, ValueError) as exc:
            failures += 1
            print(f"{system}: ERROR: {exc}. No PDF written for this system.", file=sys.stderr, flush=True)
    return 1 if failures else 0


if __name__ == "__main__":
    raise SystemExit(main())
