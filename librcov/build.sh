#!/usr/bin/env bash
# SPDX-License-Identifier: Apache-2.0
# Publication build: preserve existing files and use the pinned libbacktrace.
set -euo pipefail
source "$(dirname -- "${BASH_SOURCE[0]}")/../scripts/env.sh"
cd -- "$AE_ROOT/librcov"
AE_CC="${AE_CLANG:-clang-18}"
AE_CXX="${AE_CLANGXX:-clang++-18}"
test -f "$AE_ROOT/.deps/libbacktrace/configure"
mkdir -p build/backtrace lib libbacktrace/build
(
    cd build/backtrace
    CC="$AE_CC" CFLAGS="-O2 -fPIC" bash "$AE_ROOT/.deps/libbacktrace/configure" \
        --prefix="$AE_ROOT/librcov/libbacktrace/build" --disable-shared
    make -j "${AE_BUILD_JOBS:-8}"
    make install
)
"$AE_CXX" -std=c++17 -O3 -g -fno-omit-frame-pointer -fno-inline \
    -c librcov.cpp -o build/librcov.o -fPIC
"$AE_CXX" -std=c++17 -shared -o lib/librcov.so build/librcov.o \
    libbacktrace/build/lib/libbacktrace.a -Wl,-z,defs -fuse-ld=bfd
