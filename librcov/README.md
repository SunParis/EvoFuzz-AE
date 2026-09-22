# librcov

librcov is a lightweight runtime code coverage tracer built on top of libbacktrace. It implements the sanitizer coverage hooks required by Clang’s `-fsanitize-coverage=trace-pc-guard` instrumentation, and records unique execution paths per function and source file.

## Features

- Integrates with Clang’s sanitizer coverage (`-fsanitize-coverage=trace-pc-guard`).
- Aggregates and de-duplicates execution paths within functions.
- Groups output by source file (relative path) and function (demangled signature).
- Output can be directed to stderr or a log file, controlled by an environment variable.
- Uses libbacktrace for resolving symbols, files, and line numbers.
- Supports filtering by file name via the `RCOV_FOCUS_FILES` environment variable.

## Output

- If the environment variable `RCOV_OUT_FILE` is NOT set:
  - Output is printed to `stderr`, each line prefixed with `[RCOV]`.
- If `RCOV_OUT_FILE` is set (to any value):
  - Output is written to `$PWD/route_data.log`.

Format:
```
[RCOV] File:::: <relative_source_path>
[RCOV] Function:::: <function_signature>
[RCOV] <path_1>
[RCOV] <path_2>
...
```
- Function names are demangled and printed without return types.
- Each `<path_?>` is a unique sequence of line numbers within the function, e.g.:
  - 99 -> 104 -> 117 -> 128

## Filtering

- By default, all files are considered.
- To restrict coverage to specific files, set the `RCOV_FOCUS_FILES` environment variable to a colon-separated list of substrings. Only files whose paths contain any of these substrings will be recorded.

## Build

**Prerequisites:**
- Clang/LLVM (matching the version used for your target)
- libbacktrace (included or available via your system package manager)

**Steps:**
1. Set `CLANG_HOME` to your Clang installation root (for build.sh).
2. Run:
   ```
   ./build.sh
   ```
3. The shared library will be produced at:
   ```
   ./lib/librcov.so
   ```

**Note:** Build both librcov and your target with the same Clang version.

## Usage

1. Compile your target with sanitizer coverage:
   ```
   clang++ -g -O2 -fsanitize-coverage=trace-pc-guard -c main.cpp
   ```
2. Link with librcov:
   ```
   clang++ -o app main.o ./lib/librcov.so -rdynamic
   ```
   Or in a single step:
   ```
   clang++ -g -O2 -fsanitize-coverage=trace-pc-guard main.cpp ./lib/librcov.so -o app -rdynamic
   ```

**Notes:**
- `-rdynamic` ensures symbols are visible for backtracing.
- Make sure the runtime can locate `librcov.so` (e.g., via rpath, `LD_LIBRARY_PATH`, or absolute path).

## Controlling Output

- Default: Output to `stderr` with `[RCOV]` prefix.
- If `RCOV_OUT_FILE` is set: Output to `$PWD/route_data.log`.
- To filter files: Set `RCOV_FOCUS_FILES` to a colon-separated list of substrings.

## How it Works

- Clang injects calls to `__sanitizer_cov_trace_pc_guard_init` and `__sanitizer_cov_trace_pc_guard` at basic-block edges.
- librcov implements these hooks, maps program counters to file/function/line using libbacktrace, and aggregates unique line-number paths per function.
- On program termination, librcov emits the de-duplicated paths grouped by file and function.

## Tips

- Use the same Clang version for both librcov and your target.
- Use `-g` for better symbol and line resolution.
- Use `RCOV_FOCUS_FILES` to limit coverage to specific files if needed.
