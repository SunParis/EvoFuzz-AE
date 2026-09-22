# Third-Party Notices

The root [LICENSE](LICENSE) applies to original EvoFuzz code, analysis scripts,
and original artifact integration code. It does not relicense third-party
material. Existing file-level notices and the licenses below take precedence
for the material they cover. The accompanying paper is not licensed by this
repository's software license.

## Artemis

- Upstream: <https://github.com/test-jitcomp/Artemis>
- Reference revision: `56417b588d4cd94ce3a8350826a75205dcffea94`
- License: MIT; full text at [artemis-source/LICENSE](artemis-source/LICENSE).
- Copyright (c) 2021 Cong Li (congli@smail.nju.edu.cn, cong.li@inf.ethz.ch).
- Locations: `artemis-source/` and the `Artemis/` directories in all three
  experiment groups, excluding separately licensed JavaFuzzer files.

The Java implementation is preserved from that upstream revision. The Python
drivers have been adapted for experiment orchestration, timestamps, and
optimization-path collection. Changed Python files carry modification notices.
A Maven build descriptor was added alongside the original Gradle descriptor.
Retain the MIT copyright, permission notice, and disclaimer when redistributing
copies or substantial portions of the upstream code.

## MopFuzzer

- Upstream: <https://github.com/CGCL-codes/MopFuzzer>
- Reference revision: `4ca43c5ef928bfae478ca6a042e7e29644bae90b`
- License: Apache-2.0; full text at [mopfuzzer-source/LICENSE](mopfuzzer-source/LICENSE).
- Locations: `mopfuzzer-source/`, the `MopFuzzer/` directories, and the
  MopFuzzer-based `EvoFuzz-Coarse/` directories.

The modified implementation changes scheduling, execution accounting,
instrumentation integration, and Java dependency packaging. Modified Java
files and `pom.xml` are marked individually; Python launch drivers and report
integration are supplied alongside them. Preserve upstream copyright, patent,
trademark, and attribution notices, include Apache-2.0, and retain prominent
change notices when redistributing modified files. No root upstream NOTICE
file was found at the reference revision; this does not waive notices present
in individual files or dependencies. Apache-2.0 does not grant trademark rights.

The source tree may contain pre-existing third-party file notices. Those
notices remain applicable; an upstream repository's overall license is not a
replacement for separately identified third-party terms.

In particular, several regression examples in
`mopfuzzer-source/src/edu/hust/xzf/test/` are OpenJDK tests carrying **GPL-2.0-only**
notices, without the Classpath exception. Their Oracle copyright and GPL
headers are retained, and the GPLv2 text is available in `jvms/LICENSE`.
`FuzzerUtils.java` in that directory instead carries its own Apache-2.0 notice.
Do not describe every file in the MopFuzzer tree as Apache-2.0 or assume that a
combined binary has only the root repository's licensing obligations.

## JavaFuzzer

JavaFuzzer source is bundled under `java_fuzzer/`, `artemi/java_fuzzer/`, and
`benchmarks/JavaFuzzer/` within the experiment directories. It is licensed
under Apache-2.0. Each copy retains a LICENSE and its source-level copyright
notices. These files originate from the JavaFuzzer versions bundled with the
supplied systems, not a claim of identical revisions across systems.
Artifact-specific configuration, generated seed material, and supporting
changes are described in [MODIFICATIONS.md](MODIFICATIONS.md).

## libbacktrace

- Upstream: <https://github.com/ianlancetaylor/libbacktrace>
- Pinned revision: `b9e40069c0b47a722286b94eb5231f7f05c08713`
- License: BSD-style three-clause license; retained at
  [librcov/libbacktrace/LICENSE](librcov/libbacktrace/LICENSE).

The build downloads source at this revision. Retain upstream copyright,
conditions, and disclaimer if redistributing the library or a binary
containing it. The original `librcov` instrumentation runtime is separately
covered by the root Apache-2.0 license.

## OpenJDK

The repository distributes an instrumentation patch, not complete OpenJDK
source trees or JDK binaries. Source revisions and the patch are recorded in
`jvms/`. The patched `CompileJvm.gmk` explicitly carries GPL version 2 with
the Classpath exception; the patch follows those terms, not Apache-2.0.
Its original notice is Copyright (c) 2013, 2023, Oracle and/or its affiliates.

The upstream license and exception are reproduced in [jvms/LICENSE](jvms/LICENSE).
Consult [jvms/ADDITIONAL_LICENSE_INFO](jvms/ADDITIONAL_LICENSE_INFO) and the
downloaded source tree for component-specific terms. The Classpath exception
applies only where designated by the upstream notices, not to every OpenJDK
file or every combination with other software. Review the corresponding-source
and other GPL obligations before redistributing modified JVM binaries.

## Dependencies and Redistribution

JARs, JVM distributions, compiler distributions, and native binaries are not
versioned here. Maven, Python, and build dependencies are obtained separately
under their own licenses. In particular, compiling a fat JAR does not remove
dependency license or notice requirements. Review every included dependency
and retain applicable notices before distributing those binaries.

Using a Python wrapper does not remove the license obligations of the code it
invokes or bundles. This inventory records verified upstream licenses and the
publication changes; it is not a legal opinion or a blanket clearance of all
possible uses and redistributions.
