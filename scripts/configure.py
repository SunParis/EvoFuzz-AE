#!/usr/bin/env python3
"""Render portable templates without starting experiments or removing data."""
# SPDX-License-Identifier: Apache-2.0
import argparse
import os
from pathlib import Path
import shlex
from string import Template
import sys

import yaml

ROOT = Path(__file__).resolve().parents[1]


def expand(value, variables):
    if isinstance(value, dict):
        return {key: expand(item, variables) for key, item in value.items()}
    if isinstance(value, list):
        return [expand(item, variables) for item in value]
    return Template(value).substitute(variables) if isinstance(value, str) else value


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--jdk17", type=Path, required=True)
    parser.add_argument("--jdk21", type=Path, required=True)
    parser.add_argument("--replace-config", action="store_true",
                        help="Explicitly replace existing generated Config.yml files")
    args = parser.parse_args()
    variables = {"AE_ROOT": str(ROOT), "AE_JDK17": str(args.jdk17.resolve()),
                 "AE_JDK21": str(args.jdk21.resolve())}
    for key, value in variables.items():
        if any(char.isspace() for char in value) or any(char in value for char in "'\"`$;\\"):
            parser.error(f"{key} must use a path without whitespace or shell metacharacters")
    for key in ("AE_JDK17", "AE_JDK21"):
        for binary in ("java", "javac"):
            if not os.access(Path(variables[key]) / "bin" / binary, os.X_OK):
                parser.error(f"{key} must contain executable bin/{binary}")
    templates = sorted(ROOT.glob("*/*/Config.template.yml"))
    generated = [(path.with_name("Config.yml"), expand(yaml.safe_load(path.read_text()), variables))
                 for path in templates]
    existing = [str(path.relative_to(ROOT)) for path, _ in generated if path.exists()]
    if existing and not args.replace_config:
        parser.error("Generated configs already exist; use --replace-config after reviewing them: "
                     + ", ".join(existing))
    for path, config in generated:
        if path.is_symlink():
            parser.error(f"Refusing to overwrite symlink: {path}")
    environment = ROOT / ".local-env.sh"
    if environment.is_symlink():
        parser.error("Refusing to overwrite a symlinked .local-env.sh")
    for path, config in generated:
        path.write_text(yaml.safe_dump(config, sort_keys=False))
    environment.write_text("# Generated local settings; do not commit.\n" + "".join(
        f"export {key}={shlex.quote(value)}\n" for key, value in
        {"AE_JDK17": variables["AE_JDK17"], "AE_JDK21": variables["AE_JDK21"],
         "AE_PYTHON": sys.executable}.items()))
    (ROOT / ".tmp").mkdir(exist_ok=True)
    print(f"Rendered {len(generated)} configurations. No experiments started.")


if __name__ == "__main__":
    main()
