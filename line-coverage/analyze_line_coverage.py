#!/usr/bin/env python3
"""Plot one system's LCOV line coverage, using the scopes from Figure 5.

Runtime, C1 and C2 correspond to src/hotspot/share/{runtime,c1,opto}.
Summary includes every source file in the tracefile, including generated code
and system headers, just like the original top-level LCOV report. Repeated
source/line entries are merged; LF/LH totals must not be summed across records.

Requires matplotlib. Produces only a PDF; the input tracefile is read-only.
"""

from __future__ import annotations

import argparse
from dataclasses import dataclass
from pathlib import Path
import posixpath
import sys
import warnings


MODULES = {
    "Runtime": "/src/hotspot/share/runtime/",
    "C1": "/src/hotspot/share/c1/",
    "C2": "/src/hotspot/share/opto/",
    "Summary": None,
}
COLORS = {"EvoFuzz": "#28527d", "Artemis": "#8da6bd", "MopFuzzer": "#e8ecef"}


@dataclass(frozen=True)
class Coverage:
    module: str
    covered: int
    total: int

    @property
    def percentage(self) -> float:
        return 100.0 * self.covered / self.total


def read_coverage(info: Path) -> list[Coverage]:
    """Count unique executable (source path, line number) pairs in DA records."""
    sources: dict[str, dict[int, bool]] = {}
    current = None
    negative_counts = 0
    with info.open(encoding="utf-8") as stream:
        for record_number, raw in enumerate(stream, 1):
            record = raw.strip()
            if record.startswith("SF:"):
                if current is not None:
                    raise ValueError(f"record {record_number}: missing end_of_record")
                source = record[3:]
                if not source:
                    raise ValueError(f"record {record_number}: empty source path")
                current = sources.setdefault(posixpath.normpath(source), {})
            elif record.startswith("DA:"):
                if current is None:
                    raise ValueError(f"record {record_number}: DA without SF")
                try:
                    line_text, hits_text = record[3:].split(",", 2)[:2]
                    line_number, hits = int(line_text), int(hits_text)
                    if line_number <= 0:
                        raise ValueError("line number must be positive")
                except ValueError as exc:
                    raise ValueError(f"record {record_number}: invalid DA: {record}") from exc
                if hits < 0:
                    negative_counts += 1
                # LCOV clamps negative counters to zero before merging records.
                current[line_number] = current.get(line_number, False) or hits > 0
            elif record == "end_of_record":
                current = None
    if current is not None:
        raise ValueError("incomplete tracefile: missing final end_of_record")
    if negative_counts:
        warnings.warn(
            f"{negative_counts} negative DA counters treated as zero, as in LCOV",
            stacklevel=2,
        )

    results = []
    for module, fragment in MODULES.items():
        selected = [
            lines for source, lines in sources.items()
            if fragment is None or fragment in "/" + source
        ]
        total = sum(len(lines) for lines in selected)
        if not total:
            raise ValueError(f"no executable lines found for {module} ({fragment or 'all files'})")
        covered = sum(sum(lines.values()) for lines in selected)
        results.append(Coverage(module, covered, total))
    return results


def plot_coverage(results: list[Coverage], system: str, output: Path) -> None:
    try:
        import matplotlib
        matplotlib.use("Agg")
        from matplotlib import pyplot as plt
    except ImportError as exc:
        raise RuntimeError(
            "Matplotlib is required in the Python environment running this script. "
            "Install it with: python -m pip install matplotlib"
        ) from exc

    # Match Figure 5's serif typography, blue palette, outlines and percent axis.
    with plt.rc_context({"font.family": "serif", "pdf.fonttype": 42}):
        fig, ax = plt.subplots(figsize=(9.2, 3.8), constrained_layout=True)
        try:
            fig.patch.set_facecolor("white")
            ax.set_facecolor("white")
            bars = ax.bar(
                range(len(results)), [item.percentage for item in results],
                width=0.55, color=COLORS.get(system, "#28527d"),
                edgecolor="#2a5076", linewidth=1.6, zorder=3,
            )
            for bar, item in zip(bars, results):
                ax.annotate(
                    f"{item.percentage:.1f}%",
                    (bar.get_x() + bar.get_width() / 2, bar.get_height()),
                    xytext=(0, 5), textcoords="offset points",
                    ha="center", va="bottom", fontsize=13, fontweight="bold",
                )
            ax.set_title(system, fontsize=18, fontweight="bold", pad=12)
            ax.set_ylabel("Line Coverage", fontsize=18, fontweight="bold")
            ax.set_xticks(range(len(results)))
            ax.set_xticklabels([item.module for item in results], fontsize=18, fontweight="bold")
            ax.tick_params(axis="x", length=0, pad=8)
            ticks = list(range(0, 101, 20))
            ax.set_yticks(ticks)
            ax.set_yticklabels([f"{tick}%" for tick in ticks], fontsize=14, fontweight="bold")
            ax.set_ylim(0, 110)
            ax.grid(axis="y", color="#9a9a9a", alpha=0.45, linewidth=0.8, zorder=0)
            for spine in ax.spines.values():
                spine.set_linewidth(1.8)
                spine.set_color("#111111")
            fig.savefig(output, format="pdf", bbox_inches="tight", facecolor="white")
        finally:
            plt.close(fig)


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("info", type=Path, help="LCOV .info file")
    parser.add_argument("--system", required=True, help="System name shown above the graph")
    parser.add_argument("--output", type=Path, help="PDF path (default: input with .pdf suffix)")
    args = parser.parse_args()
    output = args.output or args.info.with_suffix(".pdf")
    if output.suffix.lower() != ".pdf":
        parser.error("--output must have a .pdf suffix")
    if output.resolve() == args.info.resolve():
        parser.error("output must not overwrite the input tracefile")
    try:
        results = read_coverage(args.info)
        plot_coverage(results, args.system, output)
    except (OSError, ValueError, RuntimeError) as exc:
        print(f"error: {exc}", file=sys.stderr)
        return 1
    print(f"Saved {output}")
    return 0


if __name__ == "__main__":
    sys.exit(main())
