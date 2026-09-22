#!/usr/bin/env bash
# Adapted for EvoFuzz-AE: portable paths and an explicit fresh-run check.
AE_SYSTEM_DIR="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)"
source "${AE_SYSTEM_DIR}/../../scripts/env.sh"
"${AE_PYTHON}" "${AE_ROOT}/scripts/check_setup.py" "${AE_SYSTEM_DIR}" || return 1 2>/dev/null || exit 1
cd -- "$AE_SYSTEM_DIR" || return 1 2>/dev/null || exit 1

export ALLFUZZER_LOG_FILE="${AE_ROOT}/line-coverage/EvoFuzz/run.log"
export TMPDIR="${AE_TMPDIR}"
export _JAVA_OPTIONS="-Djava.io.tmpdir=${AE_TMPDIR}"
export JAVA_TOOL_OPTIONS="-Djava.io.tmpdir=${AE_TMPDIR}"
export SEED_MUT_SELECTOR="random"

rm -rf ${AE_ROOT}/line-coverage/EvoFuzz/out
rm -rf ${AE_ROOT}/line-coverage/EvoFuzz/seeds
rm -rf ${AE_ROOT}/line-coverage/EvoFuzz/mut_seed_data
rm -f ${AE_ROOT}/line-coverage/EvoFuzz/run.log

/usr/bin/lcov -d ${AE_ROOT}/jvms/jdk17u-for-bug-cov/build/fastdebug-coverage/ -z > /dev/null 2>&1
cd afz && "${AE_PYTHON}" main.py ${AE_ROOT}/line-coverage/EvoFuzz/Config.yml > /dev/null 2> /dev/null &
cd ..
sleep 5
nohup bash ${AE_ROOT}/line-coverage/EvoFuzz/line-coverage.sh > /dev/null 2>&1 &
