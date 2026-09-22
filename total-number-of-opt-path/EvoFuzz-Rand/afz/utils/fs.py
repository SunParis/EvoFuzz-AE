import os
import shutil
from pathlib import Path
from typing import Optional, List

from utils.time_limit import time_limit
from utils.log import *

def write_capped(s: str, limit_mib: float = 150) -> str:
    """
    Write a string capped to limit_mib megabytes, preserving character boundaries.
    If truncation occurs, insert a "[TRUNCATED]" marker in the middle.
    150MiB default limit.
    """
    if len(s) <= 0 or limit_mib <= 0:
        return ""
    MIB = 1024 * 1024
    TRUN_STR: str = str("\n\n...[TRUNCATED]...\n\n")
    limit_l = (int)(limit_mib * MIB)
    data = s
    if len(data) <= limit_l:
        to_write = data
    else:
        half_limit = (limit_l - len(TRUN_STR)) // 2
        to_write = data[:half_limit] + TRUN_STR + data[-half_limit:]    
    return to_write

def path_join(*paths: str) -> str:
    """
    Join multiple path components into a single Path.
    """
    return str(os.path.join(*[str(p) for p in paths]))

def get_file_last_name(path: str, with_suffix: bool = True) -> str:
    """
    Get the last name of a file path, which is the file name without the directory.
    If with_suffix is False, also remove the file suffix.
    """
    p = Path(path)
    if with_suffix:
        return p.name
    else:
        return p.stem

def get_file_dir(path: str) -> str:
    """
    Get the directory of a file path.
    """
    p = Path(path)
    return str(p.parent)

def exists(path: str) -> bool:
    """
    Check if a path exists.
    """
    return os.path.exists(path)

def is_under_dir(path_s: str, dir_path_s: str) -> bool:
    """
    Check if the given path is under the specified directory.
    """
    try:
        path = Path(path_s).resolve()
        dir_path = Path(dir_path_s).resolve()
        return dir_path in path.parents
    except Exception as e:
        logging("ERROR", f"Failed to check if {path_s} is under {dir_path_s}: {e}")
        return False

def get_abs_path(path: str) -> str:
    """
    Get the absolute path of a given path.
    """
    try:
        return str(os.path.abspath(path))
    except Exception as e:
        logging("ERROR", f"Failed to get absolute path for {path}: {e}")
        return ""

def list_path_exists(lst: List[str]) -> List[str]:
    return [path for path in lst if exists(path)]

def ensure_dir(path: str) -> str:
    """
    Ensure that a directory exists.
    """
    p = Path(path)
    p.mkdir(parents=True, exist_ok=True)
    return str(p)

def mkdir(path: str, auto_rename: bool = True) -> str:
    """
    Create a directory at the specified path.
    If auto_rename is True and the directory already exists, create a unique sibling directory.
    Returns the path of the created directory.
    """
    p = Path(path)
    if p.exists():
        if auto_rename:
            new_path = unique_path(str(p))
            p = Path(new_path)
        else:
            raise FileExistsError(f"Directory already exists: {path}")
    p.mkdir(parents=True, exist_ok=False)
    return str(p)

def list_all_files_abs(root_dir: str, with_suffix: Optional[str] = None) -> List[str]:
    ret = []
    for dirpath, _, files in os.walk(root_dir):
        for filename in files:
            filename = str(filename)
            if not with_suffix or filename.endswith(with_suffix):
                ret.append(str(os.path.abspath(os.path.join(dirpath, filename))))
    return ret

def list_subdirs(root_dir: str, level: int = 1) -> List[str]:
    """
    List subdirectories of root_dir up to a certain level.
    """
    ret = []
    for dirpath, dirnames, _ in os.walk(root_dir):
        current_level = dirpath[len(root_dir):].count(os.sep)
        if current_level < level:
            ret.extend([str(os.path.join(dirpath, d)) for d in dirnames])
    return ret

def get_last_path_component(path: str, last_n: int = 4, with_preffix: str = '<...>') -> str:
    """
    Get the last n components of a path, joined by os.sep. If the path has more than n components, prefix with with_preffix.
    Example: get_last_path_component("/a/b/c/d/e/f.txt", last_n=3) -> "<...>/d/e/f.txt"
    """
    p = Path(path)
    parts = p.parts
    if len(parts) <= last_n:
        return str(p)
    else:
        return with_preffix + os.sep + os.path.join(*parts[-last_n:])

def write_text(path: str, data: str, append: bool = False, encoding: str = "utf-8", timeout_sec: int = 60) -> bool:
    """
    Write text to path. If append is True, append to the file.
    60 seconds time limit.
    """
    if data is None or len(data) == 0 or data == "":
        log_debug(f"No data to write to {path}.")
        return True
    try:
        log_debug(f"Trying to write to {path} with timeout {timeout_sec} seconds.")
        with time_limit(timeout_sec):
            dst = Path(path)
            log_debug(f"Ensuring directory for {path}.")
            ensure_dir(str(dst.parent))
            log_debug(f"Finished ensuring directory for {path}.")
            mode = "a" if append else "w"
            log_debug(f"Opening {path} in mode '{mode}' with encoding {encoding}.")
            with open(dst, mode, encoding=encoding) as f:
                log_debug(f"Write data (length={len(data)}) to {path}.")
                f.write(data)
            log_debug(f"Finished writing to {path}.")
            return True
    except Exception as e:
        logging("ERROR", f"Failed to write to {path}: {e}")
        return False

def read_text(path: str, encoding: str = "utf-8", timeout_sec: int = 60) -> Optional[str]:
    """
    Read text from a file with a time limit.
    Returns None if failed to read within the time limit or if an error occurs.
    """
    try:
        log_debug(f"Trying to read text from {path} with timeout {timeout_sec} seconds.")
        with time_limit(timeout_sec):
            with open(path, mode='r', encoding=encoding) as f:
                data = f.read()
                log_debug(f"Finished reading text from {path}. Length: {len(data)}.")
                return data
    except Exception as e:
        logging("ERROR", f"Failed to read text from {path}: {e}")
        return None

def read_lines(path: str, encoding: str = "utf-8", timeout_sec: int = 60) -> Optional[List[str]]:
    """
    Read lines from a text file with a time limit.
    Returns None if failed to read within the time limit or if an error occurs.
    """
    try:
        log_debug(f"Trying to read lines from {path} with timeout {timeout_sec} seconds.")
        with time_limit(timeout_sec):
            with open(path, mode='r', encoding=encoding) as f:
                lines = f.readlines()
                log_debug(f"Finished reading lines from {path}. Total lines: {len(lines)}.")
                return lines
    except Exception as e:
        logging("ERROR", f"Failed to read lines from {path}: {e}")
        return None

def count_str(file_path: str, target_str: str, encoding: str = "utf-8", timeout_sec: int = 60) -> Optional[int]:
    """
    Count the occurrences of target_str in the file at file_path with a time limit.
    Returns None if failed to read within the time limit or if an error occurs.
    """
    try:
        log_debug(f"Trying to count occurrences of '{target_str}' in {file_path} with timeout {timeout_sec} seconds.")
        with time_limit(timeout_sec):
            with open(file_path, mode='r', encoding=encoding) as f:
                content = f.read()
                count = content.count(target_str)
                log_debug(f"Finished counting occurrences of '{target_str}' in {file_path}. Count: {count}.")
                return count
    except Exception as e:
        logging("ERROR", f"Failed to count occurrences of '{target_str}' in {file_path}: {e}")
        return None

def clear_text(path: str) -> bool:
    """
    Clear the content of a text file.
    """
    try:
        with open(path, mode='w', encoding='utf-8') as f:
            f.truncate()
        return True
    except Exception as e:
        logging("ERROR", f"Failed to clear text file {path}: {e}")
        return False

def safe_rmtree(path: str) -> bool:
    """
    Safely remove a directory tree if it exists.
    """
    try:
        p = Path(path)
        if not p.exists():
            return False
        shutil.rmtree(p)
        return True
    except Exception as e:
        logging("ERROR", f"Failed to remove directory {path}: {e}")
        return False

def safe_remove(path: str) -> bool:
    """
    Safely remove a file if it exists.
    """
    try:
        p = Path(path)
        if not p.exists():
            return False
        p.unlink()
        return True
    except Exception as e:
        logging("ERROR", f"Failed to remove file {path}: {e}")
        return False

def unique_path(dst_: str) -> str:
    """
    If dst exists, generate a unique sibling path by appending -N.
    Example1: if dst is "dir/file.txt" and it exists, try "dir/file-1.txt", "dir/file-2.txt", etc. until a non-existing path is found.
    Example2: if dst is "dir/file-3.txt" and it exists, try "dir/file-4.txt", "dir/file-5.txt", etc. until a non-existing path is found.
    """
    dst: Path = Path(dst_)
    if not dst.exists():
        return str(dst)
    import re
    stem = dst.stem
    suffix = dst.suffix
    parent = dst.parent
    i = 1
    m = re.search(r'([0-9]+)$', stem)
    if m:
        stem = stem[:m.start()]
        i = int(m.group(1)) + 1
    while True:
        candidate = path_join(str(parent), f"{stem}{i}{suffix}")
        if not Path(candidate).exists():
            return candidate
        i += 1


def move_into(src: str, dst_dir: str, new_name: Optional[str] = None, overwrite: bool = False) -> bool:
    """
    Move a file or directory into dst_dir.
    - If new_name is provided, rename to that name under dst_dir.
    - If destination exists and overwrite=False, pick a unique name.
    """
    try:
        s = Path(src)
        if not s.exists():
            logging("ERROR", f"Source not found: {s}")
            return False

        ddir = ensure_dir(dst_dir)
        target = Path(path_join(ddir, (new_name if new_name else s.name)))

        if target.exists():
            if overwrite:
                if target.is_dir():
                    safe_rmtree(str(target))
                else:
                    target.unlink()
            else:
                target = unique_path(str(target))

        if Path(shutil.move(str(s), str(target))) == target:
            return True
        return False
    except Exception as e:
        logging("ERROR", f"Failed to move {src} to {dst_dir}: {e}")
        return False

def move_dir(src_dir: str, dst_dir: str, new_name: Optional[str] = None, overwrite: bool = False) -> bool:
    """
    Move a directory to dst_dir with optional new name.
    """
    try:
        s = Path(src_dir)
        if not s.exists():
            raise FileNotFoundError("ERROR", f"Source dir not found: {s}")
        if not s.is_dir():
            raise NotADirectoryError("ERROR", f"Source is not a directory: {s}")
    except Exception as e:
        logging("ERROR", f"Failed to move directory {src_dir} to {dst_dir}: {e}")
        return False

    return move_into(str(s), dst_dir, new_name=new_name, overwrite=overwrite)


def copy_into(src: str, dst_dir: str, new_name: Optional[str] = None, overwrite: bool = False) -> bool:
    """
    Copy a file or directory into dst_dir.
    - Directories are copied with copytree.
    - If destination exists and overwrite=False, pick a unique name.
    """
    try:
        s = Path(src)
        if not s.exists():
            logging("ERROR", f"Source not found: {s}")
            return False

        ddir = ensure_dir(dst_dir)
        target = Path(path_join(ddir, (new_name if new_name else s.name)))

        if target.exists():
            if overwrite:
                if target.is_dir():
                    safe_rmtree(str(target))
                else:
                    target.unlink()
            else:
                target = unique_path(str(target))

        if s.is_dir():
            shutil.copytree(s, target)
        else:
            shutil.copy2(s, target)

        return True
    except Exception as e:
        logging("ERROR", f"Failed to copy {src} to {dst_dir}: {e}")
        return False

def remove_class_files(dir_path: str) -> None:
    """
    Remove all .class files in dir_path.
    Returns the number of files removed.
    """
    try:
        p = Path(dir_path)
        if not p.exists() or not p.is_dir():
            logging("ERROR", f"Directory not found: {dir_path}")
            return
        for root, _, files in os.walk(p):
            for file in files:
                if file.endswith(".class"):
                    class_file_path = Path(path_join(root, file))
                    class_file_path.unlink()
    except Exception as e:
        logging("ERROR", f"Failed to remove class files in {dir_path}: {e}")
        return

def _move_to_bucket(work_dir: str, bucket_dir: str, overwrite: bool = False) -> bool:
    """
    Move a whole seed work_dir into one of the result buckets
    (e.g., timed_out, run_failed, exist_diff, success).
    """
    return move_dir(work_dir, bucket_dir, overwrite=overwrite)

def move_to_compile_failed(work_dir: str, compile_failed_dir: str, overwrite: bool = False) -> bool:
    ensure_dir(compile_failed_dir)
    return _move_to_bucket(work_dir, compile_failed_dir, overwrite=overwrite)

def move_to_run_failed(work_dir: str, run_failed_dir: str, overwrite: bool = False) -> bool:
    ensure_dir(run_failed_dir)
    return _move_to_bucket(work_dir, run_failed_dir, overwrite=overwrite)

def move_to_timed_out(work_dir: str, timed_out_dir: str, overwrite: bool = False) -> bool:
    ensure_dir(timed_out_dir)
    return _move_to_bucket(work_dir, timed_out_dir, overwrite=overwrite)

def move_to_diff(work_dir: str, diff_dir: str, overwrite: bool = False) -> bool:
    ensure_dir(diff_dir)
    return _move_to_bucket(work_dir, diff_dir, overwrite=overwrite)

def move_to_success(work_dir: str, success_dir: str, overwrite: bool = False) -> bool:
    ensure_dir(success_dir)
    return _move_to_bucket(work_dir, success_dir, overwrite=overwrite)