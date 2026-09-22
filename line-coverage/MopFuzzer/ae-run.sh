#!/usr/bin/env bash
# Adapted for EvoFuzz-AE: portable paths and an explicit fresh-run check.
AE_SYSTEM_DIR="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)"
source "${AE_SYSTEM_DIR}/../../scripts/env.sh"
"${AE_PYTHON}" "${AE_ROOT}/scripts/check_setup.py" "${AE_SYSTEM_DIR}" || return 1 2>/dev/null || exit 1
cd -- "$AE_SYSTEM_DIR" || return 1 2>/dev/null || exit 1

rm -rf mutants
rm -rf out
/usr/bin/lcov -d ${AE_ROOT}/jvms/jdk17u-for-bug-cov/build/fastdebug-coverage/ -z > /dev/null 2>&1
export _JAVA_OPTIONS="-Djava.io.tmpdir=${AE_TMPDIR}"
export JAVA_TOOL_OPTIONS="-Djava.io.tmpdir=${AE_TMPDIR}"
export TMPDIR="${AE_TMPDIR}"
export RUN_JAVA_HOME="${AE_JDK17}"
export TEST_JAVA_HOME="${AE_ROOT}/jvms/jdk17u-for-bug-cov/build/fastdebug-coverage/jdk"
mkdir -p "${AE_ROOT}/line-coverage/MopFuzzer/out"
timeout 12.5h "${AE_PYTHON}" main.py --test-java-home $TEST_JAVA_HOME \
    --run-java-home $RUN_JAVA_HOME \
    --max-iter 50 \
    --seed-dir "mopfuzzer-full" \
    > ${AE_ROOT}/line-coverage/MopFuzzer/out/out.log \
    2> ${AE_ROOT}/line-coverage/MopFuzzer/out/out.err &
nohup bash ${AE_ROOT}/line-coverage/MopFuzzer/line-coverage.sh > /dev/null 2>&1 &
