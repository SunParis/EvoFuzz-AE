import random
import time
from typing import List, Dict, Tuple
from multiprocessing import Lock, Value

from utils.cmd import run_cmd
from utils.log import *
from utils.fs import *

class CodeCov:
    
    def __init__(self, lcov_path: str, gcov_path: str, target_dir: str, timed_out: int):
        self.lcov_path = lcov_path
        self.gcov_path = gcov_path
        self.target_dir = target_dir
        self.last_rec = Value('i', -1)
        self.last_delta = Value('i', -1)
        self.last_rec_time = Value('f', time.time())
        self.timed_out = timed_out
        self.lock = Lock()
        
    def generate_cov_info(self, output_path: str) -> bool:
        """
        Generate coverage info file using lcov.
        
        :param output_path: Path to output lcov info file.
        :type output_path: str
        :return: True if successful, False otherwise.
        :rtype: bool
        """
        rc, _, err, _ = run_cmd(
            f"{self.lcov_path} "
            f"--gcov-tool {self.gcov_path} "
            f"--capture --directory {self.target_dir} "
            f"--output-file {output_path}",
            timeout=self.timed_out,
            cwd=self.target_dir
        )
        if rc != 0:
            logging("ERROR", f"Failed to generate coverage info file: {err}")
            return False
        return True    
            
    def reset_cov_info(self) -> bool:
        """
        Reset coverage info in the target directory.
        
        :return: True if successful, False otherwise.
        :rtype: bool
        """
        rc, _, err, _ = run_cmd(
            f"{self.lcov_path} --gcov-tool {self.gcov_path} --directory {self.target_dir} --zerocounters",
            timeout=self.timed_out,
            cwd=self.target_dir
        )
        if rc != 0:
            logging("ERROR", f"Failed to reset coverage info: {err}")
            return False
        return True    
    
    def get_ctxs_from_coverage(self, cov_info_path: str) -> List[Dict[str, str]] | None:
        """
        Get context snippets from the files with the lowest coverage in the given trace file.
        
        :param cov_info_path: Path to the lcov trace file.
        :type cov_info_path: str
        :return: A list of dictionaries containing 'file', 'function', and 'context' keys, or None if no data found.
        :rtype: List[Dict[str, str]] | None
        """
        with self.lock:
            self.generate_cov_info(cov_info_path)
            return CodeCov._get_ctxs_from_coverage(cov_info_path)
    
    def score_code_cov(self, cov_info_path: str, dir_segment: str = "opto") -> Tuple[int, int]:
        """
        Score code coverage by generating a temporary lcov info file and calculating total covered lines.
        If the last recorded score is less than 60 seconds old, return the cached value.
        
        :param cov_info_path: Path to lcov info file.
        :type cov_info_path: str
        :param dir_segment: Directory segment to filter files (default: "opto").
        :type dir_segment: str
        :return: A tuple containing the delta of covered lines since last record and the new total covered lines.
        :rtype: Tuple[int, int]
        """
        with self.lock:
            if self.last_rec.value >= 0 and abs(time.time() - self.last_rec_time.value) < 60:
                return self.last_delta.value, self.last_rec.value
            prev_line_num = self.last_rec.value
            self.generate_cov_info(cov_info_path)
            new_line_num = self.get_total_line(cov_info_path, dir_segment=dir_segment)
            if new_line_num <= 0:
                return self.last_delta.value, self.last_rec.value
            
            ret = new_line_num - prev_line_num if prev_line_num >= 0 else new_line_num
            
            self.last_rec.value = new_line_num
            self.last_delta.value = ret
            self.last_rec_time.value = time.time()
            
            return ret, new_line_num
    
    @staticmethod
    def _get_ctxs_from_coverage(trace_file: str) -> List[Dict[str, str]] | None:
        """
        Get context snippets from the files with the lowest coverage in the given trace file.
        
        :param trace_file: Path to the lcov trace file.
        :type trace_file: str
        :return: A list of dictionaries containing 'file', 'function', and 'context' keys, or None if no data found.
        :rtype: List[Dict[str, str]]
        """
        if not exists(trace_file):
            logging("WARNING", f"Tracefile not found: {trace_file}")
            return None
        
        files_cov = _pass1_collect_file_coverages(trace_file)
        if not files_cov:
            logging("WARNING", "No files with coverage found. Check your tracefile or filters.")
            return None

        find_file_num: int = 3
        per_file: int = 2
        bottom = files_cov[: find_file_num]
        target_files = [x[1] for x in bottom]
        target_set = set(target_files)

        details = _pass2_collect_details(
            trace_file,
            target_files_set=target_set,
            min_run_len=3,
            runs_per_file=per_file,
            random_pick=False,
            seed=None,
            context=7,
            fn_context=3,
        )
        ret = []
        # print("Lowest-coverage files:")
        for cov, fpath, LF, LH in bottom:
            # print(f"- {fpath}")
            # print(f"  Coverage: {100.0 * cov:.2f}%  (LH={LH}, LF={LF})")
            info = details.get(fpath, {})
            runs = info.get("runs", [])
            if not runs:
                # print(f"  Zero-hit consecutive ranges (>= {3}): none")
                continue
            # print(f"  Zero-hit consecutive ranges (>= {3}), with +/-{7} lines of context:")
            for (s, e, l, fn, fn_line, ctx, fn_ctx) in runs:            
                # rng = f"line {s}" if s == e else f"lines {s}-{e}"
                # print(f"    - {rng} (len={l}), function: {fn}")
                # function context
                target_fn_ctx = ""
                if fn_ctx:
                    #print(f"      Function context (+/-{3} lines from start line{'' if fn_line is None else ' ' + str(fn_line)}):")
                    for (ln, txt, is_decl) in fn_ctx:
                        target_fn_ctx += txt + "\n"
                        prefix = "F>" if is_decl else "  "
                        #print(f"      {prefix}{ln:6d}: {txt}")
                else:
                    print("      [function context unavailable]")
                    logging("WARNING", "Function context unavailable")
                    continue
                # range context
                target_code_ctx = ""
                if ctx:
                    #print(f"      Range context (+/-{7}):")
                    for (ln, txt, in_run) in ctx:
                        target_code_ctx += txt + "\n"
                        # prefix = "> " if in_run else "  "
                        # print(f"      {prefix}{ln:6d}: {txt}")
                else:
                    #print("      [source not found or unreadable]")
                    logging("WARNING", "Source context unavailable")
                    continue
                # prepare return data
                data = {}
                data["file"] = fpath
                data["function"] = target_fn_ctx
                data["context"] = target_code_ctx
                ret.append(data)
        if ret and len(ret) > 0:
            return ret
        return None
    
    @staticmethod
    def get_total_line(cov_info_path: str, dir_segment: str = "opto") -> int:
        """
        Get total covered lines in files under a specific directory segment (e.g., "opto") from lcov info file.
        0 is returned if no files matched.
        
        :param cov_info_path: Path to lcov info file.
        :type cov_info_path: str
        :param dir_segment: Directory segment to filter files (default: "opto").
        :type dir_segment: str
        :return: Total covered lines in the specified directory segment.
        :rtype: int
        """
        total = 0

        cur_file = None
        LH = None
        saw_DA = False
        da_LH = 0

        def in_opto(path: str) -> bool:
            p = path.replace("\\", "/")
            parts = p.split("/")
            return dir_segment in parts

        def finalize():
            nonlocal total, cur_file, LH, saw_DA, da_LH
            if cur_file is not None and in_opto(cur_file):
                hits = LH if LH is not None else (da_LH if saw_DA else None)
                if hits is not None:
                    total += hits
            # reset
            cur_file = None
            LH = None
            saw_DA = False
            da_LH = 0

        with open(cov_info_path, "r", encoding="utf-8", errors="ignore") as f:
            for raw in f:
                line = raw.rstrip("\n")
                if line.startswith("SF:"):
                    finalize()
                    cur_file = line[3:]
                    LH = None
                    saw_DA = False
                    da_LH = 0
                elif line.startswith("LH:"):
                    try:
                        LH = int(line[3:])
                    except ValueError:
                        pass
                elif line.startswith("DA:"):
                    # DA:<line>,<count>[,<checksum>]
                    parts = line[3:].split(",", 2)
                    if len(parts) >= 2:
                        try:
                            cnt = int(parts[1])
                            saw_DA = True
                            if cnt > 0:
                                da_LH += 1
                        except ValueError:
                            pass
                elif line == "end_of_record":
                    finalize()

        finalize()
        return total
    

def _pass1_collect_file_coverages(info_path, include_re=None, exclude_re=None):
    # return list[(coverage_ratio, file_path, LF, LH)], sorted by coverage_ratio asc
    results = []
    current_file = None
    LF = None
    LH = None
    skip = False
    # use DA fallback if LF/LH missing
    da_LF = 0
    da_LH = 0
    saw_any_DA = False

    def finalize_record():
        nonlocal current_file, LF, LH, skip, da_LF, da_LH, saw_any_DA
        if current_file is not None and not skip:
            lf = LF
            lh = LH
            if (lf is None or lh is None) and saw_any_DA:
                lf = da_LF
                lh = da_LH
            if lf is not None and lh is not None and lf > 0:
                cov = lh / lf
                if current_file and \
                    "opto" in current_file and \
                    current_file.endswith(".cpp") and \
                    "idealGraphPrinter.cpp" not in current_file and \
                    "phase.cpp" not in current_file and \
                    "c2compiler.cpp" not in current_file:
                        results.append((cov, current_file, lf, lh))
        current_file = None
        LF = None
        LH = None
        skip = False
        da_LF = 0
        da_LH = 0
        saw_any_DA = False

    with open(info_path, mode="r", encoding="utf-8", errors="ignore") as f:
        for raw in f:
            line = raw.rstrip("\n")
            if line.startswith("SF:"):
                finalize_record()
                current_file = line[3:]
                skip = False
                if include_re and not include_re.search(current_file):
                    skip = True
                if exclude_re and exclude_re.search(current_file):
                    skip = True
            elif line == "end_of_record":
                finalize_record()
            else:
                if current_file is None or skip:
                    continue
                if line.startswith("LF:"):
                    try:
                        LF = int(line[3:])
                    except ValueError:
                        pass
                elif line.startswith("LH:"):
                    try:
                        LH = int(line[3:])
                    except ValueError:
                        pass
                elif line.startswith("DA:"):
                    body = line[3:]
                    parts = body.split(",", 2)
                    if len(parts) >= 2:
                        try:
                            cnt = int(parts[1])
                            saw_any_DA = True
                            da_LF += 1
                            if cnt > 0:
                                da_LH += 1
                        except ValueError:
                            pass
    finalize_record()
    results.sort(key=lambda x: (x[0], x[1]))
    return results

def _find_zero_runs_from_DA(da_dict, min_run_len=1):
    # da_dict: line -> count
    runs = []
    if not da_dict:
        return runs
    items = sorted(da_dict.items())  # (line, count)
    i = 0
    n = len(items)
    while i < n:
        line, cnt = items[i]
        if cnt == 0:
            start = line
            end = line
            i += 1
            while i < n:
                nxt_line, nxt_cnt = items[i]
                if nxt_line == end + 1 and nxt_cnt == 0:
                    end = nxt_line
                    i += 1
                else:
                    break
            length = end - start + 1
            if length >= min_run_len:
                runs.append((start, end, length))
        else:
            i += 1
    return runs

def _get_file_lines_cached(path, cache):
    if path in cache:
        return cache[path]
    try:
        with open(path, "r", encoding="utf-8", errors="replace") as f:
            cache[path] = f.readlines()
    except Exception:
        cache[path] = None
    return cache[path]

def _pass2_collect_details(info_path, target_files_set, min_run_len=3, runs_per_file=3, random_pick=False, seed=None, context=5, fn_context=3):
    # Second pass: aggregate all zero-hit runs for each target file, then pick and generate context and function context
    if random_pick and seed is not None:
        random.seed(seed)

    runs_by_file = {}  # fpath -> list[(s,e,l)]
    fn_by_file = {}    # fpath -> list[(line, name)]
    source_cache = {}

    current_file = None
    collect = False
    da = {}
    fn = []

    def flush_record():
        nonlocal current_file, da, fn, collect
        if not collect or current_file is None:
            da = {}
            fn = []
            return
        zero_runs = _find_zero_runs_from_DA(da, min_run_len=min_run_len)
        if zero_runs:
            runs_by_file.setdefault(current_file, []).extend(zero_runs)
        if fn:
            fn_by_file.setdefault(current_file, []).extend(fn)
        da = {}
        fn = []

    with open(info_path, mode="r", encoding="utf-8", errors="ignore") as f:
        for raw in f:
            line = raw.rstrip("\n")
            if line.startswith("SF:"):
                flush_record()
                current_file = line[3:]
                collect = (current_file in target_files_set)
                da = {}
                fn = []
            elif line == "end_of_record":
                flush_record()
                current_file = None
                collect = False
                da = {}
                fn = []
            else:
                if not collect:
                    continue
                if line.startswith("DA:"):
                    body = line[3:]
                    parts = body.split(",", 2)
                    if len(parts) >= 2:
                        try:
                            lno = int(parts[0]); cnt = int(parts[1])
                            da[lno] = cnt
                        except ValueError:
                            pass
                elif line.startswith("FN:"):
                    body = line[3:]
                    parts = body.split(",", 1)
                    if len(parts) == 2:
                        try:
                            lno = int(parts[0])
                        except ValueError:
                            lno = None
                        name = parts[1]
                        if lno is not None:
                            fn.append((lno, name))
    flush_record()

    # Generate context for selected runs in each file
    details = {}
    for fpath in target_files_set:
        all_runs = runs_by_file.get(fpath, [])
        if not all_runs:
            details[fpath] = {"runs": []}
            continue
        # deduplicate
        unique = {}
        for s, e, l in all_runs:
            unique[(s, e)] = (s, e, l)
        all_runs = list(unique.values())
        if random_pick:
            picked = all_runs if len(all_runs) <= runs_per_file else random.sample(all_runs, runs_per_file)
        else:
            all_runs.sort(key=lambda x: (-x[2], x[0], x[1]))
            picked = all_runs[:runs_per_file]

        # function mapping
        fn_list = fn_by_file.get(fpath, [])
        fn_list.sort(key=lambda x: x[0])
        fn_starts = [x[0] for x in fn_list]

        def map_fn_with_line(ln):
            if not fn_list:
                return "<unknown>", None
            import bisect
            idx = bisect.bisect_right(fn_starts, ln) - 1
            if idx >= 0:
                lno, name = fn_list[idx]
                return name, lno
            return "<unknown>", None

        lines = _get_file_lines_cached(fpath, source_cache)
        mapped = []
        for s, e, l in picked:
            fname, fline = map_fn_with_line(s)
            ctx = []
            fn_ctx = []
            if lines is not None:
                total = len(lines)
                # range context
                cs = max(1, s - context)
                ce = min(total, e + context)
                for ln in range(cs, ce + 1):
                    txt = lines[ln - 1].rstrip("\n\r")
                    in_run = (s <= ln <= e)
                    ctx.append((ln, txt, in_run))
                # function context
                if fline is not None:
                    fcs = max(1, fline - fn_context)
                    fce = min(total, fline + fn_context)
                    for ln in range(fcs, fce + 1):
                        txt = lines[ln - 1].rstrip("\n\r")
                        is_decl = (ln == fline)
                        fn_ctx.append((ln, txt, is_decl))
            mapped.append((s, e, l, fname, fline, ctx, fn_ctx))
        details[fpath] = {"runs": mapped}
    return details