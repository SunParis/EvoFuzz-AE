#!/usr/bin/env bash
# SPDX-License-Identifier: Apache-2.0
set -euo pipefail
source "$(dirname -- "${BASH_SOURCE[0]}")/env.sh"
export DISABLE_ROUTE_COVERAGE_LOGGING=""
jobs="${AE_BUILD_JOBS:-8}"
gcc="${AE_GCC:-gcc-9}"
gxx="${AE_GXX:-g++-9}"
clang="${AE_CLANG:-clang-18}"
clangxx="${AE_CLANGXX:-clang++-18}"
for command in "$gcc" "$gxx" "$clang" "$clangxx"; do
    command -v "$command" >/dev/null
done
test -x "$AE_JDK17/bin/java"
bash "$AE_ROOT/librcov/build.sh"
build_jdk() (
    local tree="$1" conf="$2" cc="$3" cxx="$4"
    shift 4
    cd -- "$AE_ROOT/jvms/$tree"
    CC="$cc" CXX="$cxx" bash configure --with-boot-jdk="$AE_JDK17" \
        --disable-warnings-as-errors --with-conf-name="$conf" "$@"
    make jdk-image CONF="$conf" JOBS="$jobs"
)
build_jdk jdk17u release "$gcc" "$gxx"
build_jdk jdk17u fastdebug-coverage "$gcc" "$gxx" --enable-debug --enable-native-coverage
build_jdk jdk17u-for-bug-cov fastdebug-coverage "$gcc" "$gxx" --enable-debug --enable-native-coverage
build_jdk jdk17u-clang rcov-release "$clang" "$clangxx" --with-toolchain-type=clang
echo "JVM builds completed. Keep the checkout in this location after building."
