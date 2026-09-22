#!/usr/bin/env bash
# Adapted for EvoFuzz-AE: portable paths and an explicit fresh-run check.
AE_SYSTEM_DIR="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)"
source "${AE_SYSTEM_DIR}/../../scripts/env.sh"
"${AE_PYTHON}" "${AE_ROOT}/scripts/check_setup.py" "${AE_SYSTEM_DIR}" || return 1 2>/dev/null || exit 1
cd -- "$AE_SYSTEM_DIR" || return 1 2>/dev/null || exit 1

export ALLFUZZER_LOG_FILE="${AE_ROOT}/total-number-of-opt-path/EvoFuzz-Rand/run.log"
export TMPDIR="${AE_TMPDIR}"
export _JAVA_OPTIONS="-Djava.io.tmpdir=${AE_TMPDIR}"
export JAVA_TOOL_OPTIONS="-Djava.io.tmpdir=${AE_TMPDIR}"
export SEED_MUT_SELECTOR="random"

rm -rf ${AE_ROOT}/total-number-of-opt-path/EvoFuzz-Rand/out
rm -rf ${AE_ROOT}/total-number-of-opt-path/EvoFuzz-Rand/seeds/new_seeds
rm -rf ${AE_ROOT}/total-number-of-opt-path/EvoFuzz-Rand/mut_seed_data
rm -f ${AE_ROOT}/total-number-of-opt-path/EvoFuzz-Rand/run.log

cd afz && "${AE_PYTHON}" main.py ${AE_ROOT}/total-number-of-opt-path/EvoFuzz-Rand/Config.yml > /dev/null 2> /dev/null &
cd ..

