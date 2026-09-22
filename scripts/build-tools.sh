#!/usr/bin/env bash
# SPDX-License-Identifier: Apache-2.0
set -euo pipefail
source "$(dirname -- "${BASH_SOURCE[0]}")/env.sh"
export JAVA_HOME="$AE_JDK21"
export PATH="$JAVA_HOME/bin:$PATH"
command -v mvn >/dev/null
"$JAVA_HOME/bin/javac" -version

for project in "$AE_ROOT"/*/*/allfuzzer; do
    [[ -f "$project/pom.xml" ]] || continue
    mvn --batch-mode -f "$project/pom.xml" package
    cp -- "$project/target/AllFuzzer-1.0-SNAPSHOT.jar" "$project/AllFuzzer-1.0-SNAPSHOT.jar"
done
mvn --batch-mode -f "$AE_ROOT/mopfuzzer-source/pom.xml" package
mvn --batch-mode -f "$AE_ROOT/artemis-source/pom.xml" package
for group in line-coverage total-number-of-opt-path set-distance; do
    for system in MopFuzzer EvoFuzz-Coarse; do
        [[ -d "$AE_ROOT/$group/$system" ]] || continue
        cp -- "$AE_ROOT/mopfuzzer-source/target/MopFuzzer-1.0-jar-with-dependencies.jar" \
            "$AE_ROOT/$group/$system/MopFuzzer.jar"
    done
    cp -- "$AE_ROOT/artemis-source/target/artemis-ae-0.1.0.jar" "$AE_ROOT/$group/Artemis/artemis.jar"
done
while IFS= read -r -d '' utility; do
    "$AE_JDK17/bin/javac" -d "$(dirname -- "$utility")" "$utility"
done < <(find "$AE_ROOT/line-coverage" "$AE_ROOT/total-number-of-opt-path" "$AE_ROOT/set-distance" \
    -path '*/lib/FuzzerUtils.java' -print0)
echo "Built Java tools. No experiments started."
