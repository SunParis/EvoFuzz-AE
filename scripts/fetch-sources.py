#!/usr/bin/env python3
"""Fetch pinned public sources without resetting existing worktrees."""
# SPDX-License-Identifier: Apache-2.0
import json
import os
from pathlib import Path
import subprocess
import tempfile

ROOT = Path(__file__).resolve().parents[1]


def git(directory, *args, capture=False):
    return subprocess.run(["git", "-C", str(directory), *args], check=True,
                          text=True, stdout=subprocess.PIPE if capture else None).stdout


def fetch(directory, url, revision, patch=None):
    if directory.is_symlink():
        raise SystemExit(f"Refusing symlinked source directory: {directory}")
    if not directory.exists():
        directory.mkdir(parents=True)
        git(directory, "init")
        git(directory, "remote", "add", "origin", url)
        git(directory, "fetch", "--depth=1", "origin", revision)
        git(directory, "checkout", "--detach", "FETCH_HEAD")
    elif not (directory / ".git").is_dir():
        raise SystemExit(f"Existing non-Git directory: {directory}; inspect it manually")
    if git(directory, "rev-parse", "HEAD", capture=True).strip() != revision:
        raise SystemExit(f"Wrong revision in {directory}; no files changed")
    status = git(directory, "status", "--porcelain", "--untracked-files=no", capture=True)
    if status:
        if patch is None:
            raise SystemExit(f"Modified sources in {directory}; no files changed")
        # An earlier invocation may already have applied exactly our patch.
        with tempfile.TemporaryDirectory(prefix="evofuzz-index-") as temp:
            environment = dict(os.environ, GIT_INDEX_FILE=str(Path(temp) / "index"))
            prefix = ["git", "-C", str(directory)]
            subprocess.run(prefix + ["read-tree", "HEAD"], env=environment, check=True)
            subprocess.run(prefix + ["apply", "--cached", str(patch)], env=environment, check=True)
            matches = subprocess.run(prefix + ["diff", "--quiet"], env=environment).returncode == 0
            if not matches:
                raise SystemExit(f"Unexpected modifications in {directory}; inspect them manually")
        print(f"Already present (instrumentation applied): {directory.name}")
        return
    if patch:
        git(directory, "apply", "--check", str(patch))
        git(directory, "apply", str(patch))
    print(f"Ready: {directory.name} at {revision}")


def main():
    revisions = json.loads((ROOT / "jvms/revisions.json").read_text())
    for name in ("jdk17u", "jdk17u-clang", "jdk17u-for-bug-cov"):
        fetch(ROOT / "jvms" / name, "https://github.com/openjdk/jdk17u.git", revisions[name],
              ROOT / "jvms/patches/rcov.patch" if name == "jdk17u-clang" else None)
    fetch(ROOT / ".deps/libbacktrace", "https://github.com/ianlancetaylor/libbacktrace.git",
          revisions["libbacktrace"])


if __name__ == "__main__":
    main()
