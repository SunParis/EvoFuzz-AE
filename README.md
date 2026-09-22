# EvoFuzz Artifact Evaluation

Artifact for **EvoFuzz: IR-Aware Fuzzing for JIT Compilers through the Lens of
Node Evolution**.

This repository contains the source, experiment configurations, seed data, and
analysis scripts for the controlled HotSpot comparison and ablation study.
Each report is a separate PDF for one system.

| Paper result | Directory | Systems |
| --- | --- | --- |
| Figure 5: line coverage | `line-coverage/` | EvoFuzz, Artemis, MopFuzzer |
| Figure 6: total unique optimization paths | `total-number-of-opt-path/` | EvoFuzz, EvoFuzz-Rand, EvoFuzz-Coarse, Artemis, MopFuzzer |
| Figure 7: per-test set distance | `set-distance/` | Same five systems |

Historical results, generated mutants, compiled tools, JVM installations, and
compiler distributions are excluded. Build them before starting experiments.
This repository does not provide a one-command reproduction of the full bug
discovery campaign or the profiling-overhead experiment.

## Requirements

Use Linux x86-64 and Bash. The evaluated configuration used 24 physical CPU
cores, 192 GB RAM, and Ubuntu 20.04; runners generally use 16 workers. Run one
experiment at a time and allow substantial disk space for source builds and
generated tests. All thirteen supplied experiments take approximately a week
when executed sequentially, including setup and analysis.

Required tools: Python 3.12, Ruby, Git, Maven, GNU Make, GCC/G++ 9, GCov 9, LCOV,
Clang/Clang++ 18, GNU binutils, and the OpenJDK native build dependencies
(including autoconf, zip/unzip, X11, ALSA, CUPS, and font development headers).
See the downloaded OpenJDK `doc/building.md` for platform-specific packages.
Provide JDK 17 and JDK 21 installations yourself. The original configurations
used 17.0.2 and 21.0.2; different versions can affect generated programs and
results. These third-party distributions are not included.

No virtual environment is created by these scripts. Install Python packages
in the environment you intend to use:

```bash
git clone https://github.com/SunParis/EvoFuzz-AE.git
cd EvoFuzz-AE
python3 -m pip install -r requirements.txt
python3 scripts/configure.py --jdk17 /path/to/jdk-17.0.2 --jdk21 /path/to/jdk-21.0.2
python3 scripts/fetch-sources.py
bash scripts/build-tools.sh
bash scripts/build-jvms.sh
```

The repository and JDK paths must not contain whitespace. Configuration writes
ignored `Config.yml` files and `.local-env.sh`; it does not launch experiments.
To regenerate existing configurations, pass `--replace-config` after reviewing
local changes. Move to the final checkout location before building JVMs;
instrumentation and debug information contain build paths.

The build helpers fetch the pinned revisions in `jvms/revisions.json` and apply
`jvms/patches/rcov.patch`. They do not install operating-system packages or reset
existing source trees. Optional build variables are `AE_BUILD_JOBS` (default 8),
`AE_GCC`, `AE_GXX`, `AE_CLANG`, and `AE_CLANGXX`. Complete JVM builds can take
hours; errors from missing platform dependencies must be resolved first.

## Run an Experiment

Select a metric and system, for example:

```bash
cd total-number-of-opt-path/EvoFuzz
AE_ALLOW_CLEAN=1 bash -c 'source ./ae-run.sh; wait'
bash ./draw.sh
```

**Archive previous outputs before rerunning.** Existing launchers remove their
own previous output and working directories. `AE_ALLOW_CLEAN=1` explicitly
acknowledges that behavior; the preflight otherwise refuses to start. Do not
run the launchers in a checkout containing results you need to preserve.

Keep the shell alive, for example in a terminal multiplexer. The launchers
start background processes, so sourcing the launcher and calling `wait` is
important. A successful launcher return alone is not evidence of a valid run:
check `run.log` or `out/out.log`, error logs, and that test cases and traces are
actually being produced. Stop or wait for all workers before another experiment.

Measurement is limited to the first **12 hours**. Runners have a 12-hour-20-minute
or 12.5-hour budget to allow collection and shutdown; do not extend the report
window to match that margin. Line-coverage collection waits 43,200 seconds
after the collector starts and reads shared JVM counters, so overlapping runs
invalidate the measurement.

## Generate Reports

- **Line coverage:** run `line-coverage/<system>/ae-run.sh` as above. Its collector
  automatically writes `out/cov-hr/coverage.1.info` and `coverage.1.pdf` after the
  interval. Runtime, C1, C2, and Summary are based on LCOV `DA` line records, not
  function counts. Summary is the covered-line fraction over the full info
  file, not the average of the three component percentages.
- **Optimization paths:** run `draw.sh` in `total-number-of-opt-path/<system>/`.
  It writes `total_opt_paths.pdf`. EvoFuzz, EvoFuzz-Rand, and Artemis use recorded
  cumulative totals within the time window; MopFuzzer and EvoFuzz-Coarse count
  unique paths from successful runs, excluding failed and timed-out cases.
- **Set distance:** run `draw.sh` in `set-distance/<system>/`. It writes
  `set_distance.pdf`, a distribution of each mutant's path-set symmetric
  difference from its original seed, not an accumulated score. MopFuzzer and
  EvoFuzz-Coarse include only cases with successful execution status.

The parent `draw.sh` in either manual-report directory analyzes all five
systems. Missing or invalid data is reported as an error, not a valid zero.
Retain the raw logs, timestamps, seed traces, and mutant status files for
reanalysis. The analyzers write PDFs only; diagnostics go to the terminal.

## Reproducibility Notes

The supplied configurations are preserved rather than silently changed to
match prose descriptions. In particular, the line-coverage EvoFuzz launcher
selects the `random` scheduler and uses seed generation, while the path and
distance experiments use fixed seeds. Line-coverage Artemis also generates
seeds, and MopFuzzer uses its larger `mopfuzzer-full` corpus; these are not a
shared fixed 100-seed comparison. EvoFuzz-Rand currently permits multiple
mutators per test rather than the single-mutator description in the paper.
Review these settings before interpreting results as an exact reproduction.

Validate that Artemis produces successful mutations, not only reference runs;
an `inconsistent compilation unit` mutation error can leave no usable
set-distance samples. Fuzzing is nondeterministic: report repetitions and
configuration changes, and do not expect identical numerical results.

Publication checks cover Java-tool builds and the analyzer tests below. The
portable full-JVM builds and complete 12-hour campaigns have not been rerun
as part of preparing this source release.

Analyzer tests can be run without starting fuzzers:

```bash
python3 -m unittest discover -s line-coverage -p 'test_*.py'
python3 -m unittest discover -s total-number-of-opt-path -p 'test_*.py'
python3 -m unittest discover -s set-distance -p 'test_*.py'
```

## License

Original EvoFuzz and artifact integration code is licensed under
[Apache-2.0](LICENSE). Artemis retains MIT, MopFuzzer and JavaFuzzer retain
Apache-2.0, libbacktrace retains its BSD-style license, and the OpenJDK patch
retains its upstream GPL terms. See [THIRD_PARTY_NOTICES.md](THIRD_PARTY_NOTICES.md)
and [MODIFICATIONS.md](MODIFICATIONS.md) for attribution and modification details.
