#!/usr/bin/env bash
# SPDX-License-Identifier: Apache-2.0
AE_ROOT="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")/.." && pwd)"
if [[ -f "$AE_ROOT/.local-env.sh" ]]; then
    source "$AE_ROOT/.local-env.sh"
fi
export AE_ROOT
export AE_PYTHON="${AE_PYTHON:-python3}"
export AE_JDK17="${AE_JDK17:-$AE_ROOT/jvms/jdk-17.0.2}"
export AE_JDK21="${AE_JDK21:-$AE_ROOT/jvms/jdk-21.0.2}"
export AE_TMPDIR="${AE_TMPDIR:-$AE_ROOT/.tmp}"
export LD_LIBRARY_PATH="$AE_ROOT/librcov/lib${LD_LIBRARY_PATH:+:$LD_LIBRARY_PATH}"
