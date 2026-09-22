import argparse
import multiprocessing
import os
import random
import signal
import subprocess
import sys
import time
from concurrent.futures import FIRST_COMPLETED, wait
from multiprocessing.managers import SyncManager
from pathlib import Path
from typing import List, MutableMapping, Sequence, Set, Tuple

from pebble import ProcessPool as Pool


_TERMINATION_GRACE_SECONDS = 0.5
_PROCESS_CONTEXT = multiprocessing.get_context("fork" if os.name == "posix" else None)
_running_sessions: MutableMapping[int, int] = {}
_worker_exit_handler = None
_worker_launch_in_progress = False
_worker_termination_signal = None


def _handle_exit(signum, frame):
    print(
        f"Received signal: {signum}, preparing for graceful shutdown...",
        file=sys.stderr,
        flush=True,
    )
    raise KeyboardInterrupt


def _setup_signals():
    signal.signal(signal.SIGINT, _handle_exit)
    signal.signal(signal.SIGTERM, _handle_exit)


def _ignore_shutdown_signals():
    signal.signal(signal.SIGINT, signal.SIG_IGN)
    signal.signal(signal.SIGTERM, signal.SIG_IGN)


def _start_registry_manager() -> SyncManager:
    manager = SyncManager(ctx=_PROCESS_CONTEXT)
    manager.start(initializer=_ignore_shutdown_signals)
    return manager


def _exit_worker(signum, frame=None):
    if callable(_worker_exit_handler):
        try:
            _worker_exit_handler(signum, frame)
        except BaseException:
            pass
    os._exit(128 + signum)


def _handle_worker_exit(signum, frame):
    global _worker_termination_signal
    if _worker_launch_in_progress:
        _worker_termination_signal = signum
        return
    _exit_worker(signum, frame)


def _init_worker(running_sessions):
    global _running_sessions, _worker_exit_handler
    _running_sessions = running_sessions
    signal.signal(signal.SIGINT, signal.SIG_IGN)
    _worker_exit_handler = signal.getsignal(signal.SIGTERM)
    signal.signal(signal.SIGTERM, _handle_worker_exit)


def _registry_snapshot() -> List[int]:
    try:
        return list(_running_sessions.keys())
    except (BrokenPipeError, EOFError, OSError) as error:
        print(f"Unable to read the process-session registry: {error}", file=sys.stderr)
        return []


def _discard_session(session_id: int):
    try:
        _running_sessions.pop(session_id, None)
    except (BrokenPipeError, EOFError, OSError) as error:
        print(
            f"Unable to remove session {session_id} from the registry: {error}",
            file=sys.stderr,
        )


def _session_members(session_id: int) -> Set[int]:
    if os.name != "posix":
        return set()

    try:
        entries = os.scandir("/proc")
    except OSError as error:
        print(f"Unable to inspect process session {session_id}: {error}", file=sys.stderr)
        return set()

    members = set()
    with entries:
        for entry in entries:
            if not entry.name.isdecimal():
                continue
            pid = int(entry.name)
            try:
                if os.getsid(pid) == session_id:
                    members.add(pid)
            except (ProcessLookupError, PermissionError):
                continue
    return members


def _signal_session(session_id: int, signum: int) -> bool:
    if session_id == os.getsid(0):
        print(f"Refusing to signal our own process session {session_id}", file=sys.stderr)
        return False

    members = _session_members(session_id)
    for pid in members:
        try:
            os.kill(pid, signum)
        except ProcessLookupError:
            continue
        except OSError as error:
            print(f"Unable to send signal {signum} to PID {pid}: {error}", file=sys.stderr)
    return bool(members)


def _terminate_sessions(session_ids: Sequence[int]):
    if os.name != "posix":
        return

    remaining = {
        session_id
        for session_id in session_ids
        if session_id > 0 and _signal_session(session_id, signal.SIGTERM)
    }
    deadline = time.monotonic() + _TERMINATION_GRACE_SECONDS
    while remaining and time.monotonic() < deadline:
        remaining = {
            session_id
            for session_id in remaining
            if _signal_session(session_id, signal.SIGTERM)
        }
        if remaining:
            time.sleep(0.05)

    for session_id in remaining:
        _signal_session(session_id, signal.SIGKILL)


def kill_all_running_procs():
    session_ids = _registry_snapshot()
    _terminate_sessions(session_ids)
    for session_id in session_ids:
        if not _session_members(session_id):
            _discard_session(session_id)


def run_cmd(cmd: str | Sequence[str]) -> Tuple[int, str, str]:
    global _worker_launch_in_progress
    process = None
    session_id = None
    try:
        _worker_launch_in_progress = True
        try:
            process = subprocess.Popen(
                cmd,
                stdout=subprocess.PIPE,
                stderr=subprocess.PIPE,
                shell=isinstance(cmd, str),
                start_new_session=os.name == "posix",
                text=True,
                encoding="utf-8",
                errors="replace",
            )
            if os.name == "posix":
                session_id = process.pid
                _running_sessions[session_id] = os.getpid()
        finally:
            _worker_launch_in_progress = False
            if _worker_termination_signal is not None:
                _exit_worker(_worker_termination_signal)

        out, err = process.communicate()
        return process.returncode, out, err
    finally:
        if process is not None:
            if session_id is not None and _session_members(session_id):
                _terminate_sessions([session_id])
            elif os.name != "posix" and process.poll() is None:
                process.terminate()

            try:
                process.wait(timeout=_TERMINATION_GRACE_SECONDS)
            except subprocess.TimeoutExpired:
                process.kill()
                process.wait()

        if session_id is not None:
            _discard_session(session_id)


def worker(args):
    idx, curr_dir, run_java_home, test_java_home, rcov_java_home, seed_dir, scheduler_on, max_iter, rcov_force_on = args
    seed_full_dir = Path(str(curr_dir / "benchmarks" / "JavaFuzzer" / seed_dir))
    def list_files(dir):
        return [f for f in dir.iterdir() if f.is_file()]
    file_list = list_files(seed_full_dir)
    if len(file_list) == 0:
        print(f"No test cases found in {seed_full_dir}")
        return
    case_list = [f.stem for f in file_list if str(f.stem).startswith('Test')]
    target_seed = random.choice(case_list)
    if scheduler_on:
        # MopFuzzer will handle the scheduling,
        # so we just need to provide the seed directory
        target_seed = seed_full_dir
    project_path = str(seed_full_dir)
    cmd = [
        str(Path(run_java_home) / "bin" / "java"),
        "-jar",
        str(curr_dir / "MopFuzzer.jar"),
        "--project_path",
        project_path,
        "--target_case",
        str(target_seed),
        "--jdk",
        str(Path(test_java_home) / "bin"),
        "--max_iter",
        str(max_iter),
        "--rcov_java_home",
        str(Path(rcov_java_home)),
        "--enable_scheduler",
        str(scheduler_on),
        "--enable_profile_guide",
        "true",
        "--enable_rcov_run_any_way",
        str(rcov_force_on),
    ]
    _, out, err = run_cmd(cmd)
    print(f"Process {idx} finished running test case {target_seed}")
    print(f"\n {out}\n{err}\n")
    return

class FileData:    
    def __init__(self, file_name: str) -> None:
        self.lines: List[str] = []
        with open(file_name, 'r', encoding='utf-8') as f:
            self.lines = f.readlines()
        self.st_time: int = -1
        self.end_time: int = -1
        self.data: Set[str] = set()
        curr_file = ''
        curr_func = ''
        for lines in self.lines:
            line_str = lines.strip()
            if line_str.startswith('START_NS:::: '):
                self.st_time = int(line_str.split(':::: ')[1])
            elif line_str.startswith('END_NS:::: '):
                self.end_time = int(line_str.split(':::: ')[1])
            elif line_str.startswith('File:::: '):
                curr_file = line_str.split(':::: ')[1]
                if "opto" not in curr_file:
                    curr_file = ''
                else:
                    curr_file = curr_file[curr_file.rfind('opto'):]
            elif line_str.startswith('Function:::: '):
                if len(curr_file) > 0:
                    curr_func = line_str.split(':::: ')[1]
            elif line_str.find(':::: ') != -1:
                continue
            else:
                if len(curr_file) > 0 and len(curr_func) > 0:
                    l_str = ''
                    prev = -2
                    for line_num in line_str.split('->'):
                        try:
                            num = int(line_num.strip())
                            if num > prev:
                                l_str += f"{num},"
                                prev = num
                        except ValueError:
                            continue
                    self.data.add(curr_file + ':::: ' + curr_func + ':::: ' + l_str)

def data_process(file_dir: str, out_path: str) -> int:
    if not os.path.exists(file_dir):
        return 0
    def list_file(parent_dir: str) -> List[str]:
        import os
        file_list = []
        for root, dirs, files in os.walk(parent_dir):
            for file in files:
                file_name = str(os.path.join(root, file)).strip()
                if file_name.endswith('route_data.log'):
                    file_list.append(os.path.join(root, file))
        return file_list
    file_list = list_file(file_dir)
    if len(file_list) == 0:
        return 0
    all_data: Set[str] = set()
    out_data: List[Tuple[Tuple[int, int], Tuple[int, int]]] = []
    file_data_list: List[FileData] = []
    for file_name in file_list:
        file_data = FileData(file_name)
        file_data_list.append(file_data)
    file_data_list = sorted(file_data_list, key=lambda x: x.end_time)
    for file_data in file_data_list:
        count = 0
        total_cnt = 0
        for str_data in file_data.data:
            if str_data not in all_data:
                count += 1
                all_data.add(str_data)
            total_cnt += 1
        dddata: Tuple[Tuple[int, int], Tuple[int, int]] = (file_data.st_time, file_data.end_time), (count, total_cnt)
        out_data.append(dddata)
    ret: int = len(all_data)
    with open(out_path, 'w', encoding='utf-8') as f:
        for time_range, new_count in out_data:
            if time_range[0] > 0 and time_range[1] > 0:
                f.write(f"({time_range[0]}, {time_range[1]}) ({new_count[0]}, {new_count[1]})\n")
    return ret

def get_ns() -> str:
    return str(int(time.time() * 1e9))


def _stop_pool_and_children(pool):
    if pool is not None:
        try:
            pool.stop()
        except Exception as error:
            print(f"Unable to stop the worker pool: {error}", file=sys.stderr)

    kill_all_running_procs()

    if pool is not None:
        try:
            pool.join()
        except Exception as error:
            print(f"Unable to join the worker pool: {error}", file=sys.stderr)

    # A worker can spawn and register a child while the first sweep is running.
    kill_all_running_procs()


def _wait_for_worker(pending):
    completed, pending = wait(pending, return_when=FIRST_COMPLETED)
    for future in completed:
        future.result()
    return pending


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--proc-num", default="16", help="")
    ap.add_argument("--run-java-home", default="", help="")
    ap.add_argument("--test-java-home", default="", help="")
    ap.add_argument("--rcov-java-home", default="", help="")
    ap.add_argument("--run-num", default="10000000", help="")
    ap.add_argument("--seed-dir", default="allfuzzer-100", help="")
    ap.add_argument("--scheduler", default="false", help="")
    ap.add_argument("--max-iter", default="50", help="")
    ap.add_argument("--enable-rcov-run-any-way", default="false", help="")
    args = ap.parse_args()
    proc_num = int(args.proc_num)
    run_num = int(args.run_num)
    run_java_home = str(args.run_java_home)
    test_java_home = str(args.test_java_home)
    rcov_java_home = str(args.rcov_java_home)
    seed_dir = str(args.seed_dir)
    scheduler_on = args.scheduler.lower() == "true"
    rcov_force_on = args.enable_rcov_run_any_way.lower() == "true"
    max_iter = int(args.max_iter)
    curr_dir = Path(__file__).parent.resolve()
    path_data_dir = os.getenv("TIME_FILE", str(curr_dir / "out"))
    path_data_out = str(curr_dir / f"path_rec_mopfuzzer_{get_ns()}.txt")
    
    print(f"Start MopFuzzer. Start time: {time.strftime('%Y-%m-%d %H:%M:%S', time.localtime())}")
    pool = None
    restore_signal_handlers = False
    _setup_signals()
    manager = _start_registry_manager()
    try:
        global _running_sessions
        _running_sessions = manager.dict()
        try:
            pool = Pool(
                proc_num,
                initializer=_init_worker,
                initargs=[_running_sessions],
                context=_PROCESS_CONTEXT,
            )
            pending = set()
            for idx in range(run_num):
                if len(pending) >= proc_num:
                    pending = _wait_for_worker(pending)
                worker_args = (
                    idx,
                    curr_dir,
                    run_java_home,
                    test_java_home,
                    rcov_java_home,
                    seed_dir,
                    scheduler_on,
                    max_iter,
                    rcov_force_on,
                )
                pending.add(pool.schedule(worker, args=[worker_args]))

            while pending:
                pending = _wait_for_worker(pending)
            pool.close()
            pool.join()
        except KeyboardInterrupt:
            _ignore_shutdown_signals()
            restore_signal_handlers = True
            print("Interrupt received, terminating processes...", file=sys.stderr)
            _stop_pool_and_children(pool)
        except Exception as error:
            _ignore_shutdown_signals()
            restore_signal_handlers = True
            print(f"Error occurred: {error}", file=sys.stderr)
            _stop_pool_and_children(pool)
        finally:
            print("All processes terminated. Cleaning up...")
            kill_all_running_procs()
    finally:
        manager.shutdown()

    _running_sessions = {}
    if restore_signal_handlers:
        _setup_signals()
    print("Data processing...")
    total_path = data_process(path_data_dir, path_data_out)
    print(f"Total unique path num: {total_path}")
    print(f"All processes finished. End time: {time.strftime('%Y-%m-%d %H:%M:%S', time.localtime())}")

if __name__ == "__main__":
    main()
