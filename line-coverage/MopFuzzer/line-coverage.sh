#!/usr/bin/env bash

set -u

SCRIPT_DIR="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)"
source "${SCRIPT_DIR}/../../scripts/env.sh"
REPORT_PYTHON="${COVERAGE_REPORT_PYTHON:-${AE_PYTHON}}"

INTERVAL=43200
TOTAL_COUNT=1

COVERAGE_DIR="${AE_ROOT}/jvms/jdk17u-for-bug-cov/build/fastdebug-coverage"
GCOV_TOOL="/usr/bin/gcov-9"

OUTPUT_DIR="${AE_ROOT}/line-coverage/MopFuzzer/out/cov-hr"
mkdir -p $OUTPUT_DIR

START_SECONDS=$SECONDS

for ((i = 1; i <= TOTAL_COUNT; i++)); do
    target_seconds=$((START_SECONDS + i * INTERVAL))
    wait_seconds=$((target_seconds - SECONDS))

    if ((wait_seconds > 0)); then
        sleep "$wait_seconds"
    fi

    output_file="${OUTPUT_DIR}/coverage.${i}.info"

    /usr/bin/lcov --gcov-tool "$GCOV_TOOL" --capture --directory "$COVERAGE_DIR" --output-file "$output_file" || exit "$?"

    "$REPORT_PYTHON" "${SCRIPT_DIR}/../analyze_line_coverage.py" \
        "$output_file" --system MopFuzzer || exit "$?"
done
