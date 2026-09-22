# Artifact Modifications

This source publication preserves the experiment layout and scientific
analysis of the supplied artifact. It does not include historical run output.

## Integration Changes

- Replaced machine-specific paths with generated YAML configurations and
  shared environment settings. Removed activation of a machine-local Python
  environment. Added dependency declarations, source fetching, and build helpers.
- Added a launch preflight and an explicit acknowledgement before existing
  fresh-run cleanup. Fixed MopFuzzer's temporary-directory environment value.
- Retained independent 12-hour measurement windows, manual optimization-path
  and set-distance reports, and automatic line-coverage reporting.
- Omitted embedded Git metadata, build products, generated mutants, logs,
  databases, full JVM/compiler installations, unused seed corpora, and unused
  machine-local configurations. Original seed traces remain available.
- Removed historical command/timestamp headers from seed `.score` files and
  machine-local paths from generated comments. Optimization-path payloads and
  seed identities were preserved.

## Upstream-Derived Code

- **Artemis:** modified Python orchestration and data collection; upstream Java
  sources retained; Maven build integration added. Modified Python files carry
  notices. MIT licenses remain with the source and experiment directories.
- **MopFuzzer:** modified `pom.xml`, `Triple.java`, `Main.java`, `Scheduler.java`,
  `SchedulerV2.java`, `Configuration.java`, `JDK.java`, and `FS.java` for the
  artifact's build, instrumentation, scheduling, and execution-accounting
  integration. These files carry change notices and retain upstream terms.
  The baseline and coarse variant use the same modified Java tool with
  different driver settings.
- **JavaFuzzer:** bundled versions, configuration, and seed support are retained
  with their Apache license and original notices. Publication-specific edits
  to upstream source are marked in the affected files.
- **OpenJDK:** C2 source files receive Clang basic-block trace instrumentation;
  `libjvm` links to `librcov`. The patch changes the library path to a
  checkout-relative location and preserves the upstream build-file license.

See [THIRD_PARTY_NOTICES.md](THIRD_PARTY_NOTICES.md) for the license scope and
reference revisions. Inclusion in this artifact does not imply upstream
endorsement or that the baseline maintainers produced these modifications.
