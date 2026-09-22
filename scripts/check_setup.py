#!/usr/bin/env python3
"""Check one experiment before its launcher can clean previous output."""
# SPDX-License-Identifier: Apache-2.0
import importlib.util
import os
from pathlib import Path
import shutil
import sys

ROOT = Path(__file__).resolve().parents[1]
GROUPS = ("line-coverage", "total-number-of-opt-path", "set-distance")


def main():
    if len(sys.argv) != 2:
        raise SystemExit("Usage: check_setup.py <experiment-directory>")
    repo = Path(sys.argv[1]).resolve()
    if repo.parent.name not in GROUPS or repo.parent.parent != ROOT:
        raise SystemExit("Refusing an experiment outside this checkout")
    if any(char.isspace() for char in str(ROOT)):
        raise SystemExit("The existing experiment drivers require a checkout path without whitespace")
    errors = []
    required = []
    modules = ["yaml", "matplotlib", "numpy"]
    commands = ["timeout", "ruby"]
    is_evo = repo.name in ("EvoFuzz", "EvoFuzz-Rand")
    if is_evo:
        required += [repo / "Config.yml", repo / "allfuzzer/AllFuzzer-1.0-SNAPSHOT.jar"]
        modules += ["pebble", "openai", "tree_sitter", "tree_sitter_cpp"]
    elif repo.name == "Artemis":
        required.append(repo / "artemis.jar")
    elif repo.name in ("MopFuzzer", "EvoFuzz-Coarse"):
        required.append(repo / "MopFuzzer.jar")
        modules.append("pebble")
    else:
        raise SystemExit("Unknown system")
    java_homes = [Path(os.environ.get("AE_JDK17", ROOT / "jvms/jdk-17.0.2")),
                  Path(os.environ.get("AE_JDK21", ROOT / "jvms/jdk-21.0.2"))]
    if repo.parent.name == "line-coverage":
        java_homes.append(ROOT / "jvms/jdk17u-for-bug-cov/build/fastdebug-coverage/jdk")
        commands += ["/usr/bin/lcov", "/usr/bin/gcov-9"]
    else:
        java_homes += [ROOT / "jvms/jdk17u-clang/build/rcov-release/jdk",
                       ROOT / ("jvms/jdk17u/build/release/jdk" if is_evo
                               else "jvms/jdk17u/build/fastdebug-coverage/jdk")]
        required.append(ROOT / "librcov/lib/librcov.so")
    for path in required:
        if not path.is_file():
            errors.append(f"Missing: {path.relative_to(ROOT)}")
    for home in java_homes:
        for binary in ("java", "javac"):
            if not os.access(home / "bin" / binary, os.X_OK):
                errors.append(f"Missing executable: {home}/bin/{binary}")
    for module in modules:
        if importlib.util.find_spec(module) is None:
            errors.append(f"Missing Python module: {module}; install requirements.txt")
    for command in commands:
        if shutil.which(command) is None:
            errors.append(f"Missing command: {command}")
    if os.environ.get("AE_ALLOW_CLEAN") != "1":
        errors.append("Launchers remove prior outputs. Archive them first, then set AE_ALLOW_CLEAN=1.")
    for name in ("out", "mutants", "seeds", "mut_seed_data", "run.log"):
        if (repo / name).is_symlink():
            errors.append(f"Refusing cleanup with symlinked {name}")
    if errors:
        raise SystemExit("\n".join(errors))
    Path(os.environ.get("AE_TMPDIR", ROOT / ".tmp")).mkdir(parents=True, exist_ok=True)
    print(f"Preflight passed for {repo.parent.name}/{repo.name}", flush=True)


if __name__ == "__main__":
    main()
