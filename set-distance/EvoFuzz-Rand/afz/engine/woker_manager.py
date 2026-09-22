import traceback
from enum import Enum
from typing import Tuple, List
from abc import ABC, abstractmethod
import os, signal, time

from utils.log import *
from utils.fs import *
from utils.cmd import *
from utils.time_limit import *
from utils.mp_type import MpList
from evaluation.score import *

class Worker(ABC):
    @abstractmethod
    def run(self, idx: int, specified_seed: str | None = None) -> Tuple['WorkerManager.RunResult', Message, Score]:
        pass
    
    @abstractmethod
    def update_bases(self, score: Score) -> None:
        pass

class WorkerManager:
    
    class RunResult(Enum):
        COMPILE_FAILED = 0
        RUN_FAILED = 1
        TIMED_OUT = 2
        OUTPUT_DIFF = 3
        SUCCESS = 4
        
    WorkerInstance: Worker | None = None
    
    def __init__(self, output_dir: str, save_succes: bool, single_test_timed_out: int, re_test_num: int | None = None) -> None:
        self.single_test_timed_out = single_test_timed_out
        
        self.output_dir = output_dir
        self.compile_failed_dir = path_join(output_dir, "compile_failed")
        self.run_failed_dir = path_join(output_dir, "run_failed")
        self.timed_out_dir = path_join(output_dir, "timed_out")
        self.diff_dir = path_join(output_dir, "exist_diff")
        
        self.diff_record = MpList()
        self.compile_failed = MpList()
        self.run_failed = MpList()
        self.timed_out = MpList()
        self.run_success = MpList()
        self.is_running = MpList()
        
        self.time_list = MpList()
        self.path_rec_list = MpList()
        self.code_cov_list = MpList()
        
        self.prepare_time_rec_ns = MpList()
        self.exec_time_rec_ns = MpList()
        self.score_time_rec_ns = MpList()
        self.update_base_time_rec_ns = MpList()
        
        self.re_test_num = None
        if re_test_num is not None and re_test_num > 0:
            self.re_test_num = re_test_num
        
        self.re_test_record = MpList()
        self.re_test_record_lock = Lock()
                
        ensure_dir(self.compile_failed_dir)
        ensure_dir(self.run_failed_dir)
        ensure_dir(self.timed_out_dir)
        ensure_dir(self.diff_dir)
        
        if save_succes:
            self.success_dir = path_join(output_dir, "success")
            ensure_dir(self.success_dir)
        else:
            self.success_dir = None
    
    def process_result(self, idx: int, test_res: RunResult):
        """
        Process the result of a test run and move the work directory to the appropriate result bucket.
        
        :param idx: The index of the test run.
        :type idx: int
        :param test_res: The result of the test run as a RunResult enum.
        :type test_res: WorkerManager.RunResult
        """
        if WorkerManager.WorkerInstance is None:
            return
        target_dir = path_join(self.output_dir, str(idx))
        if test_res == WorkerManager.RunResult.COMPILE_FAILED:
            self.compile_failed.append(idx)
            move_to_compile_failed(target_dir, self.compile_failed_dir)
        elif test_res == WorkerManager.RunResult.RUN_FAILED:
            self.run_failed.append(idx)
            move_to_run_failed(target_dir, self.run_failed_dir)
        elif test_res == WorkerManager.RunResult.TIMED_OUT:
            self.timed_out.append(idx)
            move_to_timed_out(target_dir, self.timed_out_dir)
        elif test_res == WorkerManager.RunResult.OUTPUT_DIFF:
            self.diff_record.append(idx)
            move_to_diff(target_dir, self.diff_dir)
        elif test_res == WorkerManager.RunResult.SUCCESS and self.success_dir:
            self.run_success.append(idx)
            move_to_success(target_dir, self.success_dir)
        elif test_res == WorkerManager.RunResult.SUCCESS:
            self.run_success.append(idx)
            safe_rmtree(target_dir)
    
    @staticmethod
    def set_job_instance(job_instance: Worker):
        WorkerManager.WorkerInstance = job_instance
    
    def get_retest_seed(self) -> str | None:
        if self.re_test_num is None:
            return None
        try:
            with self.re_test_record_lock:
                if len(self.re_test_record) == 0:
                    return None
                cp_list = self.re_test_record.to_list()
                ret = str(cp_list[0])
                self.re_test_record.clear()
                if len(cp_list) > 1:
                    self.re_test_record.extend(cp_list[1:])
                log_debug(f"Retest record before getting: \n{cp_list}, \nafter getting: \n{self.re_test_record.to_list()}")
                return ret
        except Exception as e:
            log_info(f"Error occurred while getting retest seed: {e}")
            return None
    
    def add_retest_seed(self, seed: str):
        if self.re_test_num is None:
            return
        cnt = count_str(seed, "// AllFuzzer: insert Mutators Here")
        if cnt is None:
            log_info(f"Failed to count occurrences in seed: {seed}")
            return
        if cnt >= self.re_test_num or len(seed) == 0:
            return
        try:
            with self.re_test_record_lock:
                before_str = f"{self.re_test_record.to_list()}"
                self.re_test_record.append(seed)
                log_debug(f"Retest record before adding: \n{before_str}, \nafter adding: \n{self.re_test_record.to_list()}")
                log_debug(f"Added retest seed={seed} to retest record. cnt={cnt}")
        except Exception as e:
            log_info(f"Error occurred while adding retest seed: {e}")
    
    def run(self, idx: int) -> Tuple[Message, Score]:
        """
        Run the worker instance for the given index and handle timeouts and result processing.
        
        :param idx: The index of the test run.
        :type idx: int
        :return: A tuple containing a Message and a Score object.
        :rtype: Tuple[Message, Score]
        """
        if WorkerManager.WorkerInstance is None:
            return Message("ERROR", "WorkerInstance is not set in WorkerManager."), Score(Score.OtherData(), run_failed=True)
        
        st_ns = time.time_ns()
        
        test_res = WorkerManager.RunResult.RUN_FAILED
        msg = Message("ERROR", "Unknown error occurred.")
        score = Score(Score.OtherData(), run_failed=True)
        
        nxt_seed = self.get_retest_seed()
        self.is_running.append([idx, os.getpid()])
        
        try:
            # Set a time limit of single_test_timed_out seconds for the test run
            with time_limit(self.single_test_timed_out):
                test_res, msg, score = WorkerManager.WorkerInstance.run(idx, nxt_seed)
        except TimeoutErrorReached:
            log_info(f"Run for idx {idx} timed out. Below is the traceback: \n{traceback.format_exc()}")
            test_res = WorkerManager.RunResult.TIMED_OUT
            msg = Message("TIMEOUT", "Test run timed out.")
            score = Score(Score.OtherData(), timed_out=True)

        try:
            with time_limit(300):
                WorkerManager.WorkerInstance.update_bases(score)
        except TimeoutErrorReached:
            log_info(f"Updating bases for idx {idx} timed out. Below is the traceback: \n{traceback.format_exc()}")
        
        if score.run_failed and (test_res != WorkerManager.RunResult.COMPILE_FAILED or test_res != WorkerManager.RunResult.RUN_FAILED):
            log_info(f"Test run for idx {idx} failed according to score. Marking as run failed.")
            test_res = WorkerManager.RunResult.RUN_FAILED
        elif score.timed_out and test_res != WorkerManager.RunResult.TIMED_OUT:
            log_info(f"Test run for idx {idx} timed out according to score. Marking as timed out.")
            test_res = WorkerManager.RunResult.TIMED_OUT
        self.process_result(idx, test_res)
        
        self.is_running.remove([idx, os.getpid()])
        
        end_ns = time.time_ns()
        self.time_list.append(str((st_ns, end_ns)))
        self.path_rec_list.append(score.new_path_num)
        self.code_cov_list.append(score.new_line_num)
        
        self.prepare_time_rec_ns.append(score.data.prepare_time_ns)
        self.exec_time_rec_ns.append(score.data.exec_time_ns)
        self.score_time_rec_ns.append(score.data.score_time_ns)
        self.update_base_time_rec_ns.append(score.data.update_base_time_ns)
        
        if test_res != WorkerManager.RunResult.RUN_FAILED and \
           test_res != WorkerManager.RunResult.COMPILE_FAILED and \
           test_res != WorkerManager.RunResult.TIMED_OUT:
            self.add_retest_seed(score.data.new_seed_path)
        return msg, score
    
    def force_stop_all(self):
        """
        Forcefully stop all running worker processes.
        """
        if WorkerManager.WorkerInstance is None:
            return
        is_running_copy = self.is_running.to_list(sort=True)
        pid_list: List[int] = [int(item[1]) for item in is_running_copy]
        idx_list: List[int] = [int(item[0]) for item in is_running_copy]
        WorkerManager.kill_all_pids_wrapper(pid_list)
        for idx in idx_list:
            self.timed_out.append(idx)
            target_dir = path_join(self.output_dir, str(idx))
            move_to_timed_out(target_dir, self.timed_out_dir)
        self.is_running.clear()
        log_info(f"Test cases {idx_list} marked as timed out.")
    
    def get_fin_num(self) -> int:
        return len(list(self.timed_out)) + len(list(self.run_failed)) + \
                len(list(self.compile_failed)) + len(list(self.diff_record)) + \
                len(list(self.run_success))
    
    def get_success_list(self) -> List[int]:
        if not self.run_success:
            return []
        return self.run_success.to_list(sort=True)
    
    def get_compile_failed_list(self) -> List[int]:
        if not self.compile_failed:
            return []    
        return self.compile_failed.to_list(sort=True)
    
    def get_run_failed_list(self) -> List[int]:
        if not self.run_failed:
            return []    
        return self.run_failed.to_list(sort=True)
    
    def get_timed_out_list(self) -> List[int]:
        if not self.timed_out:
            return []    
        return self.timed_out.to_list(sort=True)
    
    def get_diff_list(self) -> List[int]:
        if not self.diff_record:
            return []    
        return self.diff_record.to_list(sort=True)
    
    def out_rec_data(self):
        rec_path = path_join(self.output_dir, "path_rec.txt")
        rec_cov = path_join(self.output_dir, "code_cov.txt")
        
        path_rec_list = self.path_rec_list.to_list()
        code_cov_list = self.code_cov_list.to_list()
        time_rec_list = self.time_list.to_list()
        
        total_cnt: int = 0
        with open(rec_path, "w") as f:
            for i in range(len(path_rec_list)):
                total_cnt += path_rec_list[i][0]
                f.write(f"{time_rec_list[i]}\t{path_rec_list[i]}\n")
        log_info(f"Total paths recorded: {total_cnt}")

        with open(rec_cov, "w") as f:
            for i in range(len(code_cov_list)):
                f.write(f"{time_rec_list[i]}\t{code_cov_list[i]}\n")
    
    def get_average_prepare_time_s(self) -> str:
        prepare_times = self.prepare_time_rec_ns.to_list()
        if not prepare_times or len(prepare_times) == 0:
            return 'Unavailable'
        ret, cnt = 0, 0
        for t in prepare_times:
            if t >= 0:
                ret += t
                cnt += 1
        if cnt == 0:
            return 'Unavailable'
        return f"{(ret / cnt) / 1e9:.4f}"
    
    def get_average_exec_time_s(self) -> str:
        exec_times = self.exec_time_rec_ns.to_list()
        if not exec_times or len(exec_times) == 0:
            return 'Unavailable'
        ret, cnt = 0, 0
        for t in exec_times:
            if t >= 0:
                ret += t
                cnt += 1
        if cnt == 0:
            return 'Unavailable'
        return f"{(ret / cnt) / 1e9:.4f}"
    
    def get_average_score_time_s(self) -> str:
        score_times = self.score_time_rec_ns.to_list()
        if not score_times or len(score_times) == 0:
            return 'Unavailable'
        ret, cnt = 0, 0
        for t in score_times:
            if t >= 0:
                ret += t
                cnt += 1
        if cnt == 0:
            return 'Unavailable'
        return f"{(ret / cnt) / 1e9:.4f}"
    
    def get_average_update_base_time_s(self) -> str:
        update_base_times = self.update_base_time_rec_ns.to_list()
        if not update_base_times or len(update_base_times) == 0:
            return 'Unavailable'
        ret, cnt = 0, 0
        for t in update_base_times:
            if t >= 0:
                ret += t
                cnt += 1
        if cnt == 0:
            return 'Unavailable'
        return f"{(ret / cnt) / 1e9:.4f}"
    
    @staticmethod
    def kill_all_pids_wrapper(running_pids: List[int]):
        """
        Kill all processes in the provided list of PIDs, handling KeyboardInterrupt gracefully.
        
        :param running_pids: List of process group IDs to be killed.
        :type running_pids: List[int]
        """
        count = 0
        while True:
            try:
                for pid in running_pids:
                    try:
                        os.killpg(pid, signal.SIGTERM)
                        time.sleep(0.5)
                        os.killpg(pid, signal.SIGKILL)
                    except Exception:
                        pass
                kill_all_pids()
                while True:
                    try:
                        ret = os.waitpid(-1, os.WNOHANG)
                        if ret == (0, 0):
                            break
                    except ChildProcessError:
                        break
                return
            except KeyboardInterrupt:
                if count >= 3:
                    print("")
                    log_info("Exceeded maximum retries for killing processes. Exiting...")
                    return
                print("")
                log_info("Received KeyboardInterrupt during killing processes. Retrying...")
                count += 1
        
    

        
    

