#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)"
source "${SCRIPT_DIR}/../scripts/env.sh"
PYTHON="$AE_PYTHON"

exec "$PYTHON" "${SCRIPT_DIR}/analyze_opt_paths.py" all \
    --root "$SCRIPT_DIR" --hours 12 "$@"
