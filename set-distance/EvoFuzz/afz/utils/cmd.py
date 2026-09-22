import resource, os, signal, time, asyncio
import signal
from typing import Tuple
from datetime import datetime

from utils.time_limit import *
from utils.mp_type import MpList
from utils.log import *
from utils.fs import *

_running_pids = MpList()

def _mem_limit(bytes_):
    def _set():
        resource.setrlimit(resource.RLIMIT_AS, (bytes_, bytes_))
    return _set

def _kill_pids(pid):
    try:
        os.killpg(pid, signal.SIGTERM)
        time.sleep(1)
        os.killpg(pid, signal.SIGKILL)
    except KeyboardInterrupt:
        # Re-raise KeyboardInterrupt to allow proper handling
        raise KeyboardInterrupt
    except Exception:
        return

def kill_all_pids(re_try_cnt = 3):
    for re_idx in range(re_try_cnt):
        try:
            lt = _running_pids.to_list()
            for pid in lt:
                try:
                    os.killpg(pid, signal.SIGTERM)
                except Exception:
                    pass
            time.sleep(2)
            for pid in lt:
                try:
                    os.killpg(pid, signal.SIGKILL)
                except Exception:
                    pass
            return
        except KeyboardInterrupt:
            log_info(f"KeyboardInterrupt received during kill_all_pids, retrying {re_idx+1}/{re_try_cnt}...")
            continue

async def _run_cmd(
    cmd: str,
    cwd: str,
    timeout: float,
    mem_bytes: int
) -> Tuple[int, str, str, float]:
    log_debug(f"Into async def _run_cmd for {cwd}.")
    log_debug(f"Starting a command in {cwd} with timeout {timeout}s")
    returncode, duration = -1, 0
    out_str, err_str = "", ""
    start_time = datetime.now()
    
    preexec = None
    if mem_bytes > 0:
        preexec = _mem_limit(mem_bytes)

    stdout = asyncio.subprocess.PIPE
    stderr = asyncio.subprocess.PIPE
    
    log_debug(f"Extracting a command run in {cwd}")
    cmd_list = cmd.split()
    for i in range(len(cmd_list)):
        if cmd_list[i].startswith('"') and cmd_list[i].endswith('"'):
            cmd_list[i] = cmd_list[i][1:-1]
        elif cmd_list[i].startswith("'") and cmd_list[i].endswith("'"):
            cmd_list[i] = cmd_list[i][1:-1]
    
    curr_ns = str(get_curr_ns())
    tmp_out_n, tmp_err_n = path_join(cwd, curr_ns + '.tmp.out'), path_join(cwd, curr_ns + '.tmp.err')
    tmp_out_f, tmp_err_f = open(tmp_out_n, 'w'), open(tmp_err_n, 'w')
    try:
        log_debug(f"Executing a command in {cwd}")
        proc = await asyncio.create_subprocess_exec(
            *cmd_list,
            cwd=cwd,
            stdout=tmp_out_f,
            stderr=tmp_err_f,
            preexec_fn=preexec,
            start_new_session=True
        )
        log_debug(f"Registering PID {proc.pid} for command in {cwd}")
        _running_pids.append(proc.pid)
        log_debug(f"Finished registering PID {proc.pid} for command in {cwd}")
    except TimeoutErrorReached:
        log_debug(f"Timeout reached while starting command in {cwd} (Before communicate. TimeoutErrorReached).")
        out_str = f"Command timed out after {timeout} seconds"
        err_str = out_str
        log_debug(f"Returning timeout error for command in {cwd} (Before communicate. TimeoutErrorReached).")
        return int(-1), str(out_str), str(err_str), float(-1)
    except Exception as e:
        log_debug(f"Exception while starting command in {cwd} (Before communicate. Exception).")
        out_str = f"Exception while starting command: {str(e)}"
        err_str = out_str
        duration = (datetime.now() - start_time).total_seconds()
        log_debug(f"Returning exception error for command in {cwd} (Before communicate. Exception).")
        return int(-1), str(out_str), str(err_str), duration
    
    out_str, err_str = str(''), str('')
    out_suffix, err_suffix = '', ''
    try:
        log_debug(f"Start to wait for command completion in {cwd}.")
        await asyncio.wait_for(proc.communicate(), timeout=timeout)
        log_debug(f"Command completed in {cwd}.")
        duration = (datetime.now() - start_time).total_seconds()
        returncode = proc.returncode
        if returncode is None:
            returncode = -1
        log_debug(f"Fin command output collected in {cwd}.")
    except asyncio.TimeoutError:
        log_debug(f"Timeout reached while executing command in {cwd} (asyncio.TimeoutError).")
        out_suffix = f"\nCommand timed out after {timeout} seconds"
        err_suffix = f"\nCommand timed out after {timeout} seconds"
        duration = float(-1)
        log_debug(f"Returning timeout error for command in {cwd} (asyncio.TimeoutError).")
        returncode = -1
    except TimeoutErrorReached:
        log_debug(f"Timeout reached while executing command in {cwd} (TimeoutErrorReached).")
        out_suffix = f"\nCommand timed out after {timeout} seconds"
        err_suffix = f"\nCommand timed out after {timeout} seconds"
        duration = float(-1)
        log_debug(f"Returning timeout error for command in {cwd} (TimeoutErrorReached).")
        returncode = -1
    except Exception as e:
        log_debug(f"Exception while executing command in {cwd} (Exception).")
        out_str = f"Exception while executing command: {str(e)}"
        err_str = f"Exception while executing command: {str(e)}"
        duration = (datetime.now() - start_time).total_seconds()
        log_debug(f"Returning exception error for command in {cwd} (Exception).")
        returncode = -1
    except BaseException as e:
        log_debug(f"Exception while executing command in {cwd} (BaseException).")
        out_str = f"Exception while executing command: {str(e)}"
        err_str = f"Exception while executing command: {str(e)}"
        duration = (datetime.now() - start_time).total_seconds()
        log_debug(f"Returning exception error for command in {cwd} (BaseException).")
        returncode = -1
    finally:
        try:
            log_debug(f"Cleaning up process for command in {cwd}.")
            _kill_pids(proc.pid)
            if proc.returncode is None:
                try:
                    await asyncio.wait_for(proc.wait(), timeout=3.0)
                except (asyncio.TimeoutError, Exception):
                    log_debug(f"Timeout or exception while waiting for process cleanup for command in {cwd}.")
                    _kill_pids(proc.pid)
        except:
            log_debug(f"Exception during cleanup for command in {cwd}.")
            pass
    
    log_debug(f"Start to read command output from temporary files in {cwd}.")
    if len(out_str) == 0:
        r_t = read_text(tmp_out_n)
        if r_t is not None:
            out_str = r_t
            safe_remove(tmp_out_n)
        else:
            out_str = 'Failed to read stdout from temporary file.'
            err_str += f'\nSee the temporary file {tmp_out_n} for details.'
        out_str += out_suffix
        log_debug(f"Finished reading stdout for command in {cwd}.")
    if len(err_str) == 0:
        r_t = read_text(tmp_err_n)
        if r_t is not None:
            err_str = r_t
            safe_remove(tmp_err_n)
        else:
            err_str = 'Failed to read stderr from temporary file.'
            err_str += f'\nSee the temporary file {tmp_err_n} for details.'
        err_str += err_suffix
        log_debug(f"Finished reading stderr for command in {cwd}.")
    
    log_debug(f"Command in {cwd} finished with return code {returncode}.")
    return returncode, str(out_str), str(err_str), duration

def run_cmd(cmd: str, cwd: str, timeout=60, mem_limit_mb: int = -1) -> Tuple[int, str, str, float]:
    """
    Executes a shell command.

    Args:
        cmd (str): Command to execute.
        cwd (str): Working directory to execute the command in.
        timeout (int, optional): Timeout in seconds for command execution. Defaults to 60.
        mem_limit_mb (int, optional): Memory limit in megabytes. Defaults to -1 (no limit).

    Returns:
        tuple: (returncode, stdout, stderr, elapsed_time)
            returncode (int): Exit code of the command.
            stdout (str): Standard output from the command.
            stderr (str): Standard error from the command.
            elapsed_time (float): Time taken to execute the command in seconds.
    """
    try:
        log_debug(f"Starting run_cmd for {cwd}.")
        with time_limit(timeout + 20):
            log_debug(f"Running _run_cmd for {cwd} in an asyncio event loop.")
            return asyncio.run(
                _run_cmd(
                    cmd=cmd,
                    cwd=cwd,
                    timeout=timeout,
                    mem_bytes=mem_limit_mb * 1024 * 1024
                )
            )
    except TimeoutErrorReached:
        log_debug(f"Command timed out after {timeout} seconds in {cwd} while running run_cmd.")
        return int(-1), f"Command timed out after {timeout} seconds", f"Command timed out after {timeout} seconds", float(-1)
    except Exception as e:
        log_debug(f"Exception in run_cmd for {cwd}: {str(e)}")
        return int(-1), f"Exception while running command: {str(e)}", f"Exception while running command: {str(e)}", float(-1)
    return int(-1), "", "", float(-1)


