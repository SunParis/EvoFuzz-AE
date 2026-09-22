# Modified for EvoFuzz-AE: experiment integration and optimization-path collection.

import os
import shlex
import signal
import sys
import time
from pathlib import Path
from typing import List
from multiprocessing import Value
from multiprocessing import Lock
from multiprocessing.managers import SyncManager
from multiprocessing.managers import ListProxy
from subprocess import Popen, \
    CompletedProcess, \
    TimeoutExpired, \
    PIPE, \
    STDOUT, \
    CalledProcessError

class MpType:
    mp_manager: SyncManager | None = None
    __mp_lock = Lock()
    __count = Value('i', 0)
    
    def __init__(self) -> None:
        with MpType.__mp_lock:
            MpType.__count.value = MpType.__count.value + 1
            if MpType.mp_manager is None:
                SyncManager.register("dict", dict)
                SyncManager.register("set", set)
                MpType.mp_manager = SyncManager()
                MpType.mp_manager.start()
    
    def __del__(self):
        with MpType.__mp_lock:
            MpType.__count.value = MpType.__count.value - 1
            if MpType.__count.value == 0 and MpType.mp_manager:
                MpType.mp_manager.shutdown()
                MpType.mp_manager = None

class MpList(MpType):
    
    def __init__(self) -> None:
        super().__init__()
        assert MpType.mp_manager is not None
        self.mp_list: ListProxy = MpType.mp_manager.list()
    
    def __str__(self) -> str:
        cp: List = self.to_list()
        return str(cp)
        
    def append(self, item) -> None:
        return self.mp_list.append(item)
        
    def extend(self, items) -> None:
        return self.mp_list.extend(items)
    
    def remove(self, items) -> None:
        return self.mp_list.remove(items)
    
    def clear(self):
        assert MpType.mp_manager is not None
        self.mp_list = MpType.mp_manager.list()
    
    def back(self):
        return self.to_list()[-1]

    def __len__(self) -> int:
        return len(self.mp_list)
    
    def __getitem__(self, index: int):
        return self.mp_list[index]
    
    def __iter__(self):
        return iter(self.mp_list)
    
    def to_list(self, sort: bool = False) -> List:
        ret: List = list(self.mp_list)
        if sort:
            ret.sort()
        return ret

_running_pids = MpList()

#
# Common os/sys utilities
#

def sys_arch_is_64():
    return sys.maxsize > 2**32


def sys_os_type():
    return sys.platform


def safe_killpg(pid, sig):
    try:
        os.killpg(pid, signal.SIGTERM)
        time.sleep(0.2)
        os.killpg(pid, signal.SIGKILL)
    except KeyboardInterrupt:
        # Re-raise KeyboardInterrupt to allow proper handling
        raise KeyboardInterrupt
    except Exception:
        return

def kill_all_running_procs():
    import os
    import signal
    for _ in range(3):
        try:
            pid_list = _running_pids.to_list()
            for pid in pid_list:
                try:
                    os.killpg(pid, signal.SIGTERM)
                except KeyboardInterrupt:
                    pass
                except Exception:
                    pass
            time.sleep(1)
            for pid in pid_list:
                try:
                    os.killpg(pid, signal.SIGKILL)
                except KeyboardInterrupt:
                    pass
                except Exception:
                    pass
            break
        except Exception as e:
            print(f"* Error while killing processes: {e}")
            time.sleep(1)


# Fix: subprocess.run(cmd) series methods, when timed out, only sends a SIGTERM
# signal to cmd while does not kill cmd's subprocess. We let each command to run
# in a new process group by adding start_new_session flag, and kill the whole
# process group such that all cmd's subprocess are also killed when timed out.
def run_proc(cmd, stdout, stderr, timeout):
    with Popen(cmd, stdout=stdout, stderr=stderr, start_new_session=True) as proc:
        _running_pids.append(proc.pid)
        try:
            output, err_msg = proc.communicate(timeout=timeout)
        except:  # Including TimeoutExpired, KeyboardInterrupt, communicate handled that.
            safe_killpg(os.getpgid(proc.pid), signal.SIGKILL)
            # We don't call proc.wait() as .__exit__ does that for us.
            raise
        retcode = proc.poll()
        if retcode is None:
            retcode = 0
    return CompletedProcess(proc.args, retcode, output, err_msg)


# We add this helper function because signal.strsignal() is added from py3.8
def signal_name(sig):
    return {
        # See `man signal`
        signal.SIGKILL: 'Killed',
        signal.SIGTERM: 'Terminated',
        signal.SIGINT: 'Interrupted',
        signal.SIGQUIT: 'Quits'
    }[sig]


def exec_time(fn, *args, **kwargs):
    begin = time.time()
    result = fn(*args, **kwargs)
    end = time.time()
    return result, (end - begin)


def format_time(delta: int) -> str:
    hour = delta // 3600
    minutes = (delta % 3600) // 60
    seconds = (delta % 3600) % 60
    if hour != 0:
        return f'{hour}h,{minutes}m,{seconds}s ({delta}s)'
    elif minutes != 0:
        return f'{minutes}m,{seconds}s ({delta}s)'
    else:
        return f'{seconds}s ({delta}s)'


#
# Common script utilities
#

class CheckError(Exception): pass


def script_check(pred: bool, msg: str):
    if not pred:
        raise CheckError(msg)


class CommandResult:

    def __init__(self, retcode, output):
        # retcode: return code of the command
        # output: stdout on success of the command, or stderr on failure
        self.retcode = retcode
        self.output = output


class Command:

    @staticmethod
    def run(cmd: str, timeout: int = 5):
        try:
            proc = run_proc(shlex.split(cmd),
                            stdout=PIPE,
                            stderr=STDOUT,
                            timeout=timeout)
            output = proc.stdout
            retcode = proc.returncode
        except CalledProcessError as x:
            output = x.output
            retcode = x.returncode
        if output is not None:
            output = str(output, encoding='utf-8').strip()
        return CommandResult(retcode, output)

    @staticmethod
    def redirected_run(cmd: str, stdout, stderr, timeout: int = 5):
        try:
            proc = run_proc(shlex.split(cmd),
                            stdout=stdout,
                            stderr=stderr,
                            timeout=timeout)
            retcode = proc.returncode
        except CalledProcessError as x:
            retcode = x.returncode
        return CommandResult(retcode, None)

    @staticmethod
    def checked_cmd(cmd: str, *args, **kwargs):
        fn = getattr(Command, cmd)
        res = fn(*args, kwargs)
        script_check(res.retcode == 0, f"Failed to run command Command.{cmd}: {res.output}")

    @classmethod
    def copy(cls, source: Path, target: Path, is_dir: bool = False):
        if is_dir:
            return cls.run(f'cp -r {source.absolute()} {target.absolute()}')
        else:
            return cls.run(f'cp {source.absolute()} {target.absolute()}')

    @classmethod
    def mkdir(cls, target: Path, can_exist: bool = False):
        if can_exist:
            return cls.run(f'mkdir -p {target.absolute()}')
        else:
            return cls.run(f'mkdir {target.absolute()}')

    @classmethod
    def move(cls, source: Path, target: Path):
        return cls.run(f'mv {source.absolute()} {target.absolute()}')

    @classmethod
    def remove(cls, path: Path, is_dir: bool, force: bool):
        args = ''
        if is_dir:
            args += '-r '
        if force:
            args += '-f '
        return cls.run(f'rm {args} {path.absolute()}')


#
# Conf type checking utilities
#

def check_conf_type(key_path: str, val, typ):
    if type(val) != typ:
        raise CheckError(f'{key_path} is not a {typ.__name__}: {val}')
    return val


def check_conf_env_var(key_path: str, var: str) -> str:
    val = os.getenv(var[1:])
    script_check(val is not None, f"{key_path}'s environment variable {var} is not set")
    return val if val is not None else ''


def check_conf_dir(key_path: str, val: str, should_exist: bool = True) -> Path:
    path = Path(val)
    if should_exist:
        script_check(path.exists(), f'{key_path} does not exist: {val}')
        script_check(path.is_dir(), f'{key_path} is not a directory: {val}')
    return path


def check_conf_file(key_path: str, val: str, should_exist: bool = True) -> Path:
    path = Path(val)
    if should_exist:
        script_check(path.exists(), f'{key_path} does not exist: {val}')
        script_check(path.is_file(), f'{key_path} is not a file: {val}')
    return path


def check_conf_file_or_dir(key_path: str, val: str) -> Path:
    path = Path(val)
    script_check(path.exists(), f'{key_path} does not exist: {val}')
    return path

# SYC
import shutil
from typing import Optional
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

def exists(path: str) -> bool:
    """
    Check if a path exists.
    """
    return os.path.exists(path)

def ensure_dir(path: str) -> str:
    """
    Ensure that a directory exists.
    """
    p = Path(path)
    p.mkdir(parents=True, exist_ok=True)
    return str(p)


def list_all_files_abs(root_dir: str, with_suffix: Optional[str] = None) -> List[str]:
    ret = []
    for dirpath, _, files in os.walk(root_dir):
        for filename in files:
            filename = str(filename)
            if not with_suffix or filename.endswith(with_suffix):
                ret.append(str(os.path.abspath(os.path.join(dirpath, filename))))
    return ret

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
        return False


def copy_into(src: str, dst_dir: str, new_name: Optional[str] = None, overwrite: bool = False) -> bool:
    """
    Copy a file or directory into dst_dir.
    - Directories are copied with copytree.
    - If destination exists and overwrite=False, pick a unique name.
    """
    try:
        s = Path(src)
        if not s.exists():
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
        return False

