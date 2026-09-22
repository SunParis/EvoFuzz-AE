import os
from enum import Enum
from typing import List, Tuple

from base.path_base import PathBase
from utils.fs import *
from utils.log import *
from utils.cmd import run_cmd



class PathCov:
    
    class RunResult(Enum):
        SUCCESS = 0
        RUN_FAILED = 1
        TIMED_OUT = 2
    
    def __init__(self, rcov_java_home: str, rcov_db_path: str, focus_files: List[str], args: str, timeout: int) -> None:
        self.path_db = PathBase(rcov_db_path)
        self.rcov_java_home = rcov_java_home
        self.args = args.strip()
        self.timeout = timeout
        if len(focus_files) > 0:
            os.environ["RCOV_FOCUS_FILES"] = ":".join(focus_files)
    
    def _run_rcov(
        self,
        run_dir: str,
        clz_path: str,
        extra_args: str
    ) -> Tuple[RunResult, str]:
        if self.rcov_java_home is None:
            return PathCov.RunResult.RUN_FAILED, ""
        
        run_res = PathCov.RunResult.SUCCESS
        cmd = (
            f'{self.rcov_java_home}/bin/java '
            f'{self.args} {extra_args} '
            f'-Dtest.jdk={self.rcov_java_home} '
            f'-Djdk.test.lib.random.seed=1 -cp {run_dir} {clz_path}'
        )
        rc, out, err, time_spent = run_cmd(cmd, cwd=run_dir, timeout=self.timeout)
        if time_spent >= self.timeout:
            run_res = PathCov.RunResult.TIMED_OUT
        elif rc != 0:
            run_res = PathCov.RunResult.RUN_FAILED
        
        ret: str = ''
        true_err: str = ''
        for line in err.splitlines():
            line = line.strip()
            if len(line) == 0:
                continue
            
            if line.startswith("[RCOV] "):
                ret += line.replace("[RCOV] ", "") + "\n"
            else:
                true_err += line + "\n"
        err = true_err
        
        if len(ret) != 0:
            write_text(
                path_join(run_dir, f"route_data.log"),
                ret,
                encoding="utf-8"
            )
        
        if rc != 0 or len(ret) == 0:
            write_text(
                path_join(run_dir, "rcov_test.out"),
                f'Java Execution Command:\n  {cmd}\n\nStandard Error Output:\n{write_capped(err, 1)}\n\nStandard Output:\n{write_capped(out, 1)}',
                encoding="utf-8"
            )
            logging("ERROR", f"RCOV execution failed with return code {rc}, see {path_join(run_dir, 'rcov_test.out')} for details.")
        return run_res, ret
    
    def score_path_cov(self, run_dir: str, clz_path: str, extra_args: str = "") -> Tuple[RunResult, int, int, int]:
        """
        Run RCOV to collect path coverage and update the path database.
        Returns the number of new paths added to the database.
        
        :param run_dir: The directory to run the Java class.
        :param clz_path: The Java class path to execute.
        :param extra_args: Additional arguments to pass to the Java execution.
        :return: A tuple (new_paths, total_paths) where new_paths is the number of new paths added and total_paths is the total number of paths in the database.
        :rtype: Tuple[int, int]
        """        
        run_res, path_rec = self._run_rcov(run_dir, clz_path, extra_args)
        log_info(f"Finished running RCOV for {get_file_last_name(run_dir, False)}. Processing path coverage data...")
        rec = self.path_db.add_path(path_rec)
        if rec == (0, 0, -1):
            run_res = PathCov.RunResult.RUN_FAILED
        return run_res, rec[0], rec[1], rec[2]
