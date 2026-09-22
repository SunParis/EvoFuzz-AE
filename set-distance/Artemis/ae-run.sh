#!/usr/bin/env bash
# Adapted for EvoFuzz-AE: portable paths and an explicit fresh-run check.
AE_SYSTEM_DIR="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)"
source "${AE_SYSTEM_DIR}/../../scripts/env.sh"
"${AE_PYTHON}" "${AE_ROOT}/scripts/check_setup.py" "${AE_SYSTEM_DIR}" || return 1 2>/dev/null || exit 1
cd -- "$AE_SYSTEM_DIR" || return 1 2>/dev/null || exit 1

rm -rf ${AE_ROOT}/set-distance/Artemis/out
mkdir -p ${AE_ROOT}/set-distance/Artemis/out
mkdir -p ${AE_ROOT}/set-distance/Artemis/out/out-log


export BLOCK_EXEC_FILE='javac'
export RCOV_FOCUS_FILES='opto'
export TIME_FILE="${AE_ROOT}/set-distance/Artemis/out/out-log"
export TMPDIR="${AE_TMPDIR}"
export _JAVA_OPTIONS="-Djava.io.tmpdir=${AE_TMPDIR}"
export JAVA_TOOL_OPTIONS="-Djava.io.tmpdir=${AE_TMPDIR}"

cd artemi/ && timeout 12.5h \
    "${AE_PYTHON}" main.py --run-java-home ${AE_JDK21} \
    --test-java-home ${AE_ROOT}/jvms/jdk17u-clang/build/rcov-release/jdk \
    --seed-dir ${AE_ROOT}/set-distance/Artemis/preproduce-seeds/allfuzzer-100 \
    > ${AE_ROOT}/set-distance/Artemis/out/out.log 2> ${AE_ROOT}/set-distance/Artemis/out/out.err &
cd ..

