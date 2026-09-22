#!/usr/bin/env bash
# Adapted for EvoFuzz-AE: portable paths and an explicit fresh-run check.
AE_SYSTEM_DIR="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)"
source "${AE_SYSTEM_DIR}/../../scripts/env.sh"
"${AE_PYTHON}" "${AE_ROOT}/scripts/check_setup.py" "${AE_SYSTEM_DIR}" || return 1 2>/dev/null || exit 1
cd -- "$AE_SYSTEM_DIR" || return 1 2>/dev/null || exit 1

rm -rf mutants
rm -rf out
rm -f ./path_rec_mopfuzzer_*.txt

export RCOV_FOCUS_FILES='opto'
export BLOCK_EXEC_FILE='javac'
export TIME_FILE="${AE_ROOT}/total-number-of-opt-path/MopFuzzer/out"
export _JAVA_OPTIONS="-Djava.io.tmpdir=${AE_TMPDIR}"
export JAVA_TOOL_OPTIONS="-Djava.io.tmpdir=${AE_TMPDIR}"
export TMPDIR="${AE_TMPDIR}"
export RUN_JAVA_HOME="${AE_JDK17}/"
export TEST_JAVA_HOME="${AE_ROOT}/jvms/jdk17u/build/fastdebug-coverage/jdk"
export RCOV_JAVA_HOME="${AE_ROOT}/jvms/jdk17u-clang/build/rcov-release/jdk"
mkdir -p "${AE_ROOT}/total-number-of-opt-path/MopFuzzer/out"
timeout 12.5h "${AE_PYTHON}" main.py --test-java-home $TEST_JAVA_HOME \
    --run-java-home $RUN_JAVA_HOME --rcov-java-home $RCOV_JAVA_HOME \
    --max-iter 50 \
    --seed-dir "allfuzzer-100" \
    --enable-rcov-run-any-way "true" \
    --scheduler "false" \
    > ${AE_ROOT}/total-number-of-opt-path/MopFuzzer/out/out.log \
    2> ${AE_ROOT}/total-number-of-opt-path/MopFuzzer/out/out.err &
