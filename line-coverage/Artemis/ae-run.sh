#!/usr/bin/env bash
# Adapted for EvoFuzz-AE: portable paths and an explicit fresh-run check.
AE_SYSTEM_DIR="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)"
source "${AE_SYSTEM_DIR}/../../scripts/env.sh"
"${AE_PYTHON}" "${AE_ROOT}/scripts/check_setup.py" "${AE_SYSTEM_DIR}" || return 1 2>/dev/null || exit 1
cd -- "$AE_SYSTEM_DIR" || return 1 2>/dev/null || exit 1

rm -rf ${AE_ROOT}/line-coverage/Artemis/out
mkdir -p ${AE_ROOT}/line-coverage/Artemis/out
/usr/bin/lcov -d ${AE_ROOT}/jvms/jdk17u-for-bug-cov/build/fastdebug-coverage/ -z > /dev/null 2>&1
export TMPDIR="${AE_TMPDIR}"
export _JAVA_OPTIONS="-Djava.io.tmpdir=${AE_TMPDIR}"
export JAVA_TOOL_OPTIONS="-Djava.io.tmpdir=${AE_TMPDIR}"
cd artemi/ && timeout 12.5h \
    "${AE_PYTHON}" main.py --run-java-home ${AE_JDK21} \
    --test-java-home ${AE_ROOT}/jvms/jdk17u-for-bug-cov/build/fastdebug-coverage/jdk \
    > ${AE_ROOT}/line-coverage/Artemis/out/out.log 2> ${AE_ROOT}/line-coverage/Artemis/out/out.err &
cd ..
nohup bash ${AE_ROOT}/line-coverage/Artemis/line-coverage.sh > ${AE_ROOT}/line-coverage/Artemis/out/hourly_lcov.log 2>&1 &
