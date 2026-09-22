
import traceback
from typing import Tuple
from pebble import ProcessPool
from multiprocessing import Lock, cpu_count
from concurrent.futures import TimeoutError

from base.seed_base import *
from base.mutator_base import *
from seed_mut_selector.api import *
from config.config import *
from utils.log import *
from utils.fs import *
from utils.llm import *
from runners.jar_runner import *
from runners.java_runner import *
from runners.javafuzzer_runner import *
from runners.jtreg_runner import *
from evaluation.path_coverage import *
from evaluation.code_coverage import *
from engine.woker_manager import *


from synthesis.code_coverage import CodeCovMutGenerator
from synthesis.path_coverage import PathCovMutGenerator
from synthesis.synthesis import MutGenerator
    
@staticmethod
def _handle_exit(signum, frame):
    log_info(f"Received signal: {signum}, preparing for graceful shutdown...")
    raise KeyboardInterrupt

@staticmethod
def _setup_signals():
    signal.signal(signal.SIGTERM, _handle_exit)


woker_manager: WorkerManager | None = None

class FuzzingTest(Worker):
    
    def __init__(self, config: Config, seed_mut_selector: Selector,
        code_cov: CodeCov | None = None, path_cov: PathCov | None = None
    ) -> None:
        
        self.allfuzzer_runner = AllFuzzerJarRunner(
            run_java_home=config.run_java_home,
            jar_path=config.allfuzzer.jar_path,
            stmt_cnt=config.allfuzzer.max_statements,
            array_size=config.allfuzzer.max_array_length,
            ignore_list_path=config.allfuzzer.ignore_list_path,
            timeout=config.timed_out,
            insert_one_point=config.allfuzzer.insert_in_one_point,
            insert_in_order=config.allfuzzer.insert_in_order,
            insert_in_prev_point_iteration=config.allfuzzer.insert_in_prev_point_iteration
        )
        
        javac_libs: List[str] = []
        if config.seed_gen_config.java_fuzzer:
            javac_libs += config.seed_gen_config.java_fuzzer.java_fuzzer_lib
        if config.seed_gen_config.jtreg_test:
            javac_libs += config.seed_gen_config.jtreg_test.jtreg_lib
        if config.additional_java_lib_paths:
            javac_libs += config.additional_java_lib_paths
        self.java_runner = JavaRunner(
            java_home=config.test_java_home,
            verified_java_home=config.verified_java_home,
            verified_openjdk_home=config.verified_openjdk_home,
            args=config.test_java_args,
            timeout=config.timed_out,
            memory_limit_mb=config.test_memory_limit,
            libs=javac_libs
        )
        self.is_j9 = self.java_runner.is_openj9()
        
        self.base_lock = Lock()
        
        # seedbase config
        self.seed_base = SeedBase(
            config.mut_seed_sel_config.seed_dir,
            self.base_lock,
            config.seed_gen_config.possiblity_of_new_seed,
            seed_mut_selector
        )
        self.seed_base.add_seed_generator(
            JavaFuzzerRunner(
                ruby_path=config.seed_gen_config.java_fuzzer.ruby_path,
                java_fuzzer_home=config.seed_gen_config.java_fuzzer.java_fuzzer_dir,
                java_fuzzer_config_path=config.seed_gen_config.java_fuzzer.config_path
            ) if config.seed_gen_config.java_fuzzer else None,
            config.seed_gen_config.java_fuzzer.priority if config.seed_gen_config.java_fuzzer else 0
        )
        self.seed_base.add_seed_generator(
            JTregRunner(
                jtreg_seed_home=config.seed_gen_config.jtreg_test.jtreg_test_home,
                seed_list_path=config.seed_gen_config.jtreg_test.seed_list
            ) if config.seed_gen_config.jtreg_test else None,
            config.seed_gen_config.jtreg_test.priority if config.seed_gen_config.jtreg_test else 0
        )
        
        # mutatorbase config
        self.mutator_base = MutatorBase(
            seed_mut_selector,
            mut_checkser=self.check_mutator,
            base_lock=self.base_lock
        )
        
        self.code_coverage = code_cov        
        self.path_coverage = path_cov        
        self.output_dir = config.output_dir
        
        self.skip_bug_detection = config.skip_bug_detection
    
    @staticmethod
    def _ensure_dirs_(work_dir: str):
        ensure_dir(work_dir)
        ensure_dir(path_join(work_dir, "out"))
        ensure_dir(path_join(work_dir, "tmp"))
        
    @staticmethod
    def _prepare_essential_files_(work_dir: str, origin_seed: str, new_seed: str) -> Tuple[bool, Message]:
        '''
        Prepare essential files for fuzzing test:
          - Move original Seed to tmp/
          - Move new Seed from out/ to work_dir/
          - Clean up out/ directory
        Returns:
          - (bool, Message): Success flag and message
        '''
        # Move original Seed to tmp
        move_res = move_into(origin_seed, path_join(work_dir, "tmp"), overwrite=True)
        if not move_res:
            return False, Message("ERROR", f"Moving Seed to work_dir/tmp failed.")
        # Move new Seed to work_dir
        move_res = move_into(new_seed, work_dir, overwrite=True)
        if not move_res:
            return False, Message("ERROR", f"Moving new Seed to work_dir failed.")
        # Clean up out directory
        rmr_res = safe_rmtree(path_join(work_dir, "out"))
        if not rmr_res:
            return False, Message("ERROR", f"Cleaning work_dir/out directory failed.")
        
        return True, Message("INFO", f"Essential files prepared successfully.")
    
    @staticmethod
    def parse_package_and_class(src_path: str):
        import re
        pkg = None
        try:
            with Path(src_path).open("r", encoding="utf-8", errors="ignore") as f:
                head = f.read(8192)
            m = re.search(r'^\s*package\s+([a-zA-Z_][\w\.]*)\s*;', head, flags=re.MULTILINE)
            if m:
                pkg = m.group(1).strip()
        except Exception:
            pass
        cls = Path(src_path).stem
        if pkg:
            return f"{pkg}.{cls}"
        else:
            return cls
    
    def check_mutator(self, mut_path: str, test_dir: str) -> Tuple[bool, Message]:
        test_file_name: str = "Test.java"
        
        # Generate a simple Hello World Java file as a seed
        seed_path, _, _, msg = SeedBase.hello_world_java(path_join(test_dir, test_file_name))
        if not seed_path:
            return False, Message("ERROR", f"{msg.data}")
        
        # Use AllFuzzer jar to process and generate a new {test_file_name}
        write_text(path_join(test_dir, "MUTATOR_LIST"), mut_path + "\n")
        run_success, msg = self.allfuzzer_runner.run(
            test_dir,
            seed_path,
            path_join(test_dir, "out"),
            path_join(test_dir, "data.json"),
            path_join(test_dir, "MUTATOR_LIST")
        )
        if not run_success:
            return False, Message("ERROR", f"{msg.data}")
        
        # Prepare essential files
        run_success, msg = FuzzingTest._prepare_essential_files_(
            # We can ensure the new seed is at test_dir/{test_file_name}
            test_dir, path_join(test_dir, test_file_name), path_join(test_dir, "out", test_file_name)
        )
        if not run_success:
            return False, Message("ERROR", f"{msg.data}")
        
        # Compile the new {test_file_name} with javac
        run_success, msg = self.java_runner.compile(
            test_dir,
            path_join(test_dir, test_file_name),
            path_join(test_dir, "javac.compile.log"),
            path_join(test_dir, "javac.compile.err"),
            verified=True
        )
        if not run_success:
            return False, Message("ERROR", f"{msg.data}")

        jit_res, msg, _ = self.java_runner.run(
            test_dir,
            opt_lv=JavaOptLevel.MIXED,
            std_out_path=path_join(test_dir, "with_jit.log"),
            err_out_path=path_join(test_dir, "with_jit.err"),
            clz_path=FuzzingTest.parse_package_and_class(seed_path)
        )
        if jit_res == RunJavaStatus.TIMEOUT or jit_res == RunJavaStatus.ERROR:
            return False, Message("ERROR", f"{msg.data}")
        self.mutator_base.add_mutator(mut_path)
        return True, Message("INFO", f"Mutator check: Mutator {mut_path} passed the check.")        
    
    def get_output_dir(self) -> str:
        return self.output_dir

    def update_bases(self, score: Score) -> None:
        st_ns = time.time_ns()
        mut_select: str = score.data.mut_select_list
        new_seed: str = score.data.new_seed_path
        org_seed: str = score.data.org_seed_path
        
        # Update SeedBase with new seed if applicable
        if not score.run_failed and not score.timed_out:
            self.seed_base.add_seed(new_seed, org_seed, score)
        self.mutator_base.update_score(org_seed, mut_select, score)
        end_ns = time.time_ns()
        score.data.update_base_time_ns = end_ns - st_ns
    
    def run(self, idx: int, specified_seed: str | None = None) -> Tuple[WorkerManager.RunResult, Message, Score]:
        log_info(f"Process {idx}: Processing...")

        test_case_st_time = get_curr_ns()
        work_dir = path_join(self.output_dir, f"{idx}")
        test_case_data: 'Score.OtherData' = Score.OtherData()
        FuzzingTest._ensure_dirs_(work_dir)
        
        # Get seed for this test case
        if specified_seed is None:
            org_seed_path, target_seed_path, compile_args, run_args, msg = \
                self.seed_base.get_seed(work_dir)
            if not org_seed_path or not target_seed_path:
                return WorkerManager.RunResult.COMPILE_FAILED, \
                    Message("ERROR", f"Process {idx}: {msg.data}"), Score(test_case_data, run_failed=True)
            log_info(f"Process {idx}: {msg.data}")
        else:
            copy_into(specified_seed, work_dir, overwrite=True)
            org_seed_path = specified_seed
            target_seed_path = path_join(work_dir, get_file_last_name(specified_seed))
            compile_args = SeedBase.get_compile_args_value(specified_seed)
            run_args = SeedBase.get_run_args_value(specified_seed)
            log_info(f"Process {idx}: Using specified seed {specified_seed}.")
        test_case_data.org_seed_path = org_seed_path
        
        # Generate MUTATOR_LIST file
        mutator_selected, mut_num = self.mutator_base.get_mutator_list(org_seed_path)
        write_text(
            path_join(work_dir, "MUTATOR_LIST"),
            mutator_selected
        )
        log_info(f"Process {idx}: Generated MUTATOR_LIST.")
        test_case_data.mut_select_list = mutator_selected
        
        # Use AllFuzzer jar to process and generate a new test file
        run_success, msg = self.allfuzzer_runner.run(
            work_dir,
            target_seed_path,
            path_join(work_dir, "out"),
            path_join(work_dir, "data.json"),
            path_join(work_dir, "MUTATOR_LIST"),
            mut_num
        )
        if not run_success:
            return WorkerManager.RunResult.COMPILE_FAILED, \
                Message("ERROR", f"Process {idx}: {msg.data}"), Score(test_case_data, run_failed=True)
        log_info(f"Process {idx}: {msg.data}")
        
        # The out file of AllFuzzer is under work_dir/ now,
        #   which has the same path as origin seed_path
        all_fuzzer_out: str = target_seed_path
        test_case_data.new_seed_path = all_fuzzer_out
                
        # Prepare essential files
        run_success, msg = FuzzingTest._prepare_essential_files_(
            work_dir, target_seed_path, path_join(
                str(Path(target_seed_path).parent), "out",
                str(Path(target_seed_path).name)
            )
        )
        if not run_success:
            return WorkerManager.RunResult.COMPILE_FAILED, \
                Message("ERROR", f"Process {idx}: {msg.data}"), Score(test_case_data, run_failed=True)
        
        pkg_clz: str = FuzzingTest.parse_package_and_class(all_fuzzer_out)
        test_case_prepare_end_ns = get_curr_ns()
        test_case_data.prepare_time_ns = test_case_prepare_end_ns - test_case_st_time
        log_info(f"Process {idx}: {msg.data}")
        
        # Compile the new test file with javac
        log_info(f"Process {idx}: Compiling the new test file with javac...")
        run_success, msg = self.java_runner.compile(
            work_dir,
            all_fuzzer_out,
            path_join(work_dir, "javac.compile.log"),
            path_join(work_dir, "javac.compile.err"),
            extra_args=compile_args
        )
        if not run_success:
            return WorkerManager.RunResult.COMPILE_FAILED, \
                Message("ERROR", f"Process {idx}: {msg.data}"), Score(test_case_data, run_failed=True)
        log_info(f"Process {idx}: {msg.data}")
        
        find_bug: bool = False
        
        if not self.skip_bug_detection:        
            # Run the java program with JIT in high optimization level
            jit_out_scr: Tuple[str, str] | None = None
            if self.is_j9:
                log_info(f"Process {idx}: Running with JIT (Scorch) for early bug detection...")
                jit_res, msg, jit_out_scr = self.java_runner.run(
                    work_dir,
                    std_out_path=path_join(work_dir, "with_jit_scorch.log"),
                    err_out_path=path_join(work_dir, "with_jit_scorch.err"),
                    clz_path=pkg_clz,
                    extra_args=run_args,
                    opt_lv=JavaOptLevel.JIT_COMPCOMPILED
                )
                if jit_res == RunJavaStatus.TIMEOUT:
                    return WorkerManager.RunResult.TIMED_OUT, \
                        Message("ERROR", f"Process {idx}: {msg.data}"), Score(test_case_data, timed_out=True)
                elif jit_res == RunJavaStatus.ERROR:
                    return WorkerManager.RunResult.RUN_FAILED, \
                        Message("ERROR", f"Process {idx}: {msg.data}"), Score(test_case_data, run_failed=True)
                elif jit_res == RunJavaStatus.ABORT:
                    log_info(f"Process {idx}: {msg.data}")
                    find_bug = True
                    msg = Message("INFO", f"Process {idx}: Execution with JIT aborted, viewed as output difference.")
                else:
                    log_info(f"Process {idx}: {msg.data}")
            
            # Run the java program with JIT
            jit_out: Tuple[str, str] = (str(''), str(''))
            if not find_bug:
                log_info(f"Process {idx}: Running with JIT for bug detection...")
                prefix: str = "with_jit"
                if self.is_j9:
                    prefix = "with_jit_scorch_200"
                jit_res, msg, jit_out = self.java_runner.run(
                    work_dir,
                    std_out_path=path_join(work_dir, f"{prefix}.log"),
                    err_out_path=path_join(work_dir, f"{prefix}.err"),
                    clz_path=pkg_clz,
                    extra_args=run_args,
                    opt_lv=JavaOptLevel.JIT_COMPILED
                )
                log_debug(f"Finish running with jit cmd: {work_dir}.")
                if jit_res == RunJavaStatus.TIMEOUT:
                    return WorkerManager.RunResult.TIMED_OUT, \
                        Message("ERROR", f"Process {idx}: {msg.data}"), Score(test_case_data, timed_out=True)
                elif jit_res == RunJavaStatus.ERROR:
                    return WorkerManager.RunResult.RUN_FAILED, \
                        Message("ERROR", f"Process {idx}: {msg.data}"), Score(test_case_data, run_failed=True)
                elif jit_res == RunJavaStatus.ABORT:
                    log_info(f"Process {idx}: {msg.data}")
                    find_bug = True
                    msg = Message("INFO", f"Process {idx}: Execution with JIT aborted, viewed as output difference.")
                else:
                    log_info(f"Process {idx}: {msg.data}")
            log_debug(f"Finish fuzzing with jit: {work_dir}.")
            
            # Run the java program with Mixed
            mixed_out: Tuple[str, str] = (str(''), str(''))
            if not find_bug:
                prefix: str = "with_mixed"
                if self.is_j9:
                    prefix = "with_jit_hot"
                log_info(f"Process {idx}: Running with Mixed for bug detection...")
                mixed_res, msg, mixed_out = self.java_runner.run(
                    work_dir,
                    std_out_path=path_join(work_dir, f"{prefix}.log"),
                    err_out_path=path_join(work_dir, f"{prefix}.err"),
                    clz_path=pkg_clz,
                    extra_args=run_args,
                    opt_lv=JavaOptLevel.MIXED
                )
                if mixed_res == RunJavaStatus.TIMEOUT:
                    return WorkerManager.RunResult.TIMED_OUT, \
                        Message("ERROR", f"Process {idx}: {msg.data}"), Score(test_case_data, timed_out=True)
                elif mixed_res == RunJavaStatus.ERROR:
                    return WorkerManager.RunResult.RUN_FAILED, \
                        Message("ERROR", f"Process {idx}: {msg.data}"), Score(test_case_data, run_failed=True)
                elif mixed_res == RunJavaStatus.ABORT:
                    log_info(f"Process {idx}: {msg.data}")
                    find_bug = True
                    msg = Message("INFO", f"Process {idx}: Execution with Mixed aborted, viewed as output difference.")
                else:
                    log_info(f"Process {idx}: {msg.data}")
                
            # Run the java program without JIT
            without_jit_out: Tuple[str, str] = (str(''), str(''))
            if not find_bug:
                # Compile the new test file with javac
                if not self.java_runner.same_java_home():
                    log_info(f"Process {idx}: Java home for running without JIT is different from compiling, recompiling with that java home...")
                    run_success, msg = self.java_runner.compile(
                        work_dir,
                        all_fuzzer_out,
                        path_join(work_dir, "javac.compile.verified.log"),
                        path_join(work_dir, "javac.compile.verified.err"),
                        extra_args=compile_args,
                        verified=True
                    )
                    if not run_success:
                        return WorkerManager.RunResult.COMPILE_FAILED, \
                            Message("ERROR", f"Process {idx}: {msg.data}"), Score(test_case_data, run_failed=True)
                    log_info(f"Process {idx}: {msg.data}")
                log_info(f"Process {idx}: Running without JIT for bug detection...")
                without_jit_res, msg, without_jit_out = self.java_runner.run(
                    work_dir,
                    std_out_path=path_join(work_dir, "without_jit.log"),
                    err_out_path=path_join(work_dir, "without_jit.err"),
                    clz_path=pkg_clz,
                    extra_args=run_args,
                    verified=True,
                    opt_lv=JavaOptLevel.INTERPRETED
                )
                if without_jit_res == RunJavaStatus.TIMEOUT:
                    return WorkerManager.RunResult.TIMED_OUT, \
                        Message("ERROR", f"Process {idx}: {msg.data}"), Score(test_case_data, timed_out=True)
                elif without_jit_res == RunJavaStatus.ERROR:
                    return WorkerManager.RunResult.RUN_FAILED, \
                        Message("ERROR", f"Process {idx}: {msg.data}"), Score(test_case_data, run_failed=True)
                else:
                    log_info(f"Process {idx}: {msg.data}")
                    
                if jit_out[0] != without_jit_out[0] or \
                jit_out[1] != without_jit_out[1]:
                    msg: Message = Message("WARNING", f"Process {idx}: Outputs differ between running with and without JIT, see *.err and *.log for details.")
                    find_bug = True
                elif mixed_out[0] != without_jit_out[0] or \
                mixed_out[1] != without_jit_out[1]:
                    msg: Message = Message("WARNING", f"Process {idx}: Outputs differ between running with Mixed and without JIT, see *.err and *.log for details.")
                    find_bug = True
                elif jit_out_scr is not None and \
                    (   jit_out_scr[0] != without_jit_out[0] or \
                        jit_out_scr[1] != without_jit_out[1]
                    ):
                    msg: Message = Message("WARNING", f"Process {idx}: Outputs differ between running with JIT (Scorch) and without JIT, see *.err and *.log for details.")
                else:
                    msg: Message = Message("INFO", f"Process {idx}: Executed successfully with consistent outputs.")
            
            # Verified again with openjdk if needed
            verified_openjdk_out: Tuple[str, str] = ("", "")
            if not find_bug and self.is_j9:
                log_info(f"Process {idx}: Running verified OpenJDK for cross-checking...")
                ret_code, msg = self.java_runner.compile_verified_openjdk(
                    work_dir,
                    all_fuzzer_out,
                    path_join(work_dir, "compile.verified.openjdk.log"),
                    path_join(work_dir, "compile.verified.openjdk.err"),
                    extra_args=compile_args
                )
                if not ret_code:
                    return WorkerManager.RunResult.COMPILE_FAILED, \
                        Message("ERROR", f"Process {idx}: {msg.data}"), Score(test_case_data, run_failed=True)
                log_info(f"Process {idx}: {msg.data}")
                verified_openjdk_res, msg, verified_openjdk_out = self.java_runner.run_verified_openjdk(
                    work_dir,
                    std_out_path=path_join(work_dir, "run.verified.openjdk.log"),
                    err_out_path=path_join(work_dir, "run.verified.openjdk.err"),
                    clz_path=pkg_clz,
                    extra_args=run_args
                )
                if verified_openjdk_res == RunJavaStatus.TIMEOUT:
                    return WorkerManager.RunResult.TIMED_OUT, \
                        Message("ERROR", f"Process {idx}: {msg.data}"), Score(test_case_data, timed_out=True)
                elif verified_openjdk_res == RunJavaStatus.ERROR:
                    return WorkerManager.RunResult.RUN_FAILED, \
                        Message("ERROR", f"Process {idx}: {msg.data}"), Score(test_case_data, run_failed=True)
                else:
                    log_info(f"Process {idx}: {msg.data}")
                
                if verified_openjdk_out[0] != without_jit_out[0] or \
                verified_openjdk_out[1] != without_jit_out[1]:
                    msg: Message = Message("WARNING", f"Process {idx}: Outputs differ between running without JIT on OpenJ9 and OpenJDK, \
                        see *.err and *.log for details.")
                    find_bug = True
                else:
                    log_info(f"Process {idx}: Verified on OpenJDK with consistent outputs.")
        else:
            log_info(f"Process {idx}: skipping JIT bug tests, directly scoring the test case.")
        
        test_case_exec_end_ns = get_curr_ns()
        test_case_data.exec_time_ns = test_case_exec_end_ns - test_case_prepare_end_ns        
        log_info(f"Process {idx}: Start scoring from path coverage...")
        run_res, new_path, curr_tt_path, all_tt_path = self.path_coverage.score_path_cov(
            work_dir,
            pkg_clz,
            run_args
        ) if self.path_coverage is not None else (PathCov.RunResult.SUCCESS, 0, 0, -1)
        new_path_num = new_path, curr_tt_path, all_tt_path
        test_case_data.score_data_path = path_join(work_dir, "route_data.log") \
            if new_path_num != (0, 0, -1) else ""
        test_case_data.prev_score_data_path = path_join(get_file_dir(org_seed_path), get_file_last_name(org_seed_path, False) + ".score") \
            if exists(path_join(get_file_dir(org_seed_path), get_file_last_name(org_seed_path, False) + ".score")) else ""
        
        log_info(f"Process {idx}: Start scoring from code coverage...")
        code_cov_line_num = self.code_coverage.score_code_cov(
            str(path_join(work_dir, "coverage.info"))
        ) if self.code_coverage is not None else (0, 0)
        
        test_case_score_end_ns = get_curr_ns()
        test_case_data.score_time_ns = test_case_score_end_ns - test_case_exec_end_ns
        score_obj = Score(
            test_case_data,
            find_bug=find_bug,
            path_num=new_path_num,
            line_num=code_cov_line_num,
            run_failed=(run_res == PathCov.RunResult.RUN_FAILED),
            timed_out=(run_res == PathCov.RunResult.TIMED_OUT)
        )
        log_info(f"Process {idx}: Scoring completed.")
        
        if find_bug:
            return WorkerManager.RunResult.OUTPUT_DIFF, msg, score_obj
        return WorkerManager.RunResult.SUCCESS, msg, score_obj

def worker(args) -> None:
    if woker_manager is None:
        raise RuntimeError("WorkerManager is not initialized in worker process.")
    idx = args
    try:
        msg, score = woker_manager.run(idx)
        log_message(msg)
        log_info(f"Worker process ({idx}) completed. Has Score: {score}")
    except KeyboardInterrupt:
        log_info(f"Worker process ({idx}) received KeyboardInterrupt. Exiting...")
    except TimeoutError:
        log_info(f"Worker process ({idx}) timed out. Exiting...")
    except Exception as e:
        logging("ERROR", f"Worker process ({idx}) encountered an exception: {str(e)}\nwith traceback:\n{traceback.format_exc()}")

def approx_single_test_time(config: Config) -> int:
    ret: int = 0
    ret += config.timed_out * 3
    if config.llm:
        ret += config.llm.timed_out * 2
    if config.code_coverage:
        ret += config.code_coverage.timed_out
    if config.path_coverage:
        ret += config.path_coverage.timed_out
    
    return ret

def approx_total_time(test_num: int, config: Config) -> int:
    ret: int = 0
    ret += test_num * config.timed_out * 3
    if config.llm:
        ret += (test_num // (config.llm.generate_after_mutation_num + 1)) * config.llm.timed_out * 2
    if config.code_coverage:
        ret += test_num * config.code_coverage.timed_out
    if config.path_coverage:
        ret += test_num * config.path_coverage.timed_out
    
    ret = (int)((ret * 1.1) / config.threads) # 10% buffer for overhead
    
    return ret

def fuzzing(config: Config):
    global woker_manager
    
    # If thread_num exceeds CPU count, give a warning
    if config.threads > cpu_count():
        log_info(f"Warning: Specified thread_num {config.threads} exceeds CPU count {cpu_count()}.")
    max_parallel = config.threads
    log_info(f"Using up to {max_parallel} parallel processes.")
    
    woker_manager = WorkerManager(
        config.output_dir,
        config.save_success,
        approx_single_test_time(config),
        config.allfuzzer.insert_in_prev_point_iteration
    )
    
    scheler = os.getenv('SEED_MUT_SELECTOR')
    if scheler is None:
        scheler = 'score_based'
    else:
        scheler = scheler.strip()
    log_info(f"Using seed-mutator selector: '{scheler}'")
    seed_mut_selector: Selector = create_selector(
        scheler,
        config.mut_seed_sel_config,
        config.allfuzzer.insert_in_prev_point_iteration > 0
    )
        
    code_coverage = CodeCov(
        config.code_coverage.lcov_path,
        config.code_coverage.gcov_path,
        config.code_coverage.target_dir,
        config.code_coverage.timed_out
    ) if config.code_coverage else None
    
    path_coverage = PathCov(
        config.path_coverage.test_java_home,
        config.path_coverage.data_base_path,
        config.path_coverage.focus_files,
        str(config.test_java_args).strip() +
        " " + config.path_coverage.run_args.strip(),
        config.path_coverage.timed_out
    ) if config.path_coverage else None
    
    llm_client: LLMClient | None = LLMClient(
        config.llm.api_key,
        config.llm.base_url,
        config.llm.model,
        config.llm.timed_out
    ) if config.llm else None
    
    mutator_generators: List[MutGenerator | None] = []
    mutator_generators.append(
        CodeCovMutGenerator(
            llm_client,
            str(path_join(config.output_dir, "coverage.info")),
            code_coverage.get_ctxs_from_coverage
        ) if llm_client and code_coverage else None
    )
    mutator_generators.append(
        PathCovMutGenerator(
            llm_client,
            path_coverage.path_db.get_all_paths
        ) if llm_client and path_coverage else None
    )
    
    woker_manager.set_job_instance(
        FuzzingTest(
            config, seed_mut_selector,
            code_coverage, path_coverage
        )
    )
    
    total_time = config.total_test_time
    time_left = total_time
    
    total_test_num = config.total_test_num
    test_per_iter = min(total_test_num, 500 * max_parallel)
    if config.llm:
       test_per_iter = min(config.llm.generate_after_mutation_num + 1, test_per_iter)
    
    has_test_num = 0
    counter = 0
    pool = None
    
    _setup_signals()
        
    try:
        while True:  
            pool = ProcessPool(max_parallel)
            args_iter = [i for i in range(has_test_num + 1, has_test_num + test_per_iter + 1)]
            approx_time_sec = approx_total_time(test_per_iter, config)
            time_limi = min(approx_time_sec, time_left)
            
            iter_start_time = datetime.now()
            try:
                with time_limit(time_limi + 5):
                    log_info(f"Starting iteration: Testing {test_per_iter} cases with time limit {time_limi} seconds.")
                    res = pool.map(worker, args_iter, timeout=time_limi)
                    iter = res.result()
                    while True:
                        try:
                            iter.next()
                        except StopIteration:
                            break
                        except TimeoutErrorReached:
                            raise TimeoutErrorReached()
                        except Exception as e:
                            logging("ERROR", f"An error occurred in worker process: {e}\n{traceback.format_exc()}")
            except TimeoutError:
                log_info("Time limit for current iteration reached.")
                if pool is not None:
                    pool.stop()
                    pool.join()
                    pool = None
            except TimeoutErrorReached:
                log_info("Time limit for current iteration reached.")
                if pool is not None:
                    pool.stop()
                    pool.join()
                    pool = None
            iter_end_time = datetime.now()
            
            has_test_num += test_per_iter
            counter += test_per_iter
            time_left -= (int)((iter_end_time - iter_start_time).total_seconds())
            
            # Dynamically adjust test_per_iter based on remaining time
            test_per_iter = max(max_parallel, int(0.8 * \
                test_per_iter * time_left / (datetime.now() - iter_start_time).total_seconds()))
            test_per_iter = min(test_per_iter, 50 * max_parallel)
            if config.llm:
                test_per_iter = min(config.llm.generate_after_mutation_num + 1, test_per_iter)
            
            if time_left <= 0:
                log_info("Time limit reached. Stopping further processing.")
                raise TimeoutError
            if total_test_num > 0 and has_test_num >= total_test_num:
                break
            
            if config.llm and \
               counter >= config.llm.generate_after_mutation_num and \
               mutator_generators:
                
                llm_start_time = datetime.now()
                
                log_info("Calling LLM to generate code bricks...")
                llm_gen_dir = path_join(config.allfuzzer.code_bricks_dir, "llm_generated")
                for mut_gen in mutator_generators:
                    if mut_gen is None:
                        continue
                    new_muts = mut_gen.generate(llm_gen_dir)
                    if new_muts is not None and len(new_muts) > 0:
                        ensure_dir(path_join(config.output_dir, "test"))
                        test_dir = path_join(config.output_dir, "test")
                        for mut in new_muts:
                            check_res = MutatorBase.is_valid_mutator(mut, test_dir)
                            if not check_res:
                                move_into(mut, path_join(config.output_dir, "bad_mutators"), overwrite=False)
                        safe_rmtree(test_dir)
                    counter = 0
                log_info(f"Code bricks generated from LLM output at {str(llm_gen_dir)}.")
                
                llm_endtime = datetime.now()
                time_left -= (int)((llm_endtime - llm_start_time).total_seconds())
                
                if time_left <= 0:
                    log_info("Time limit reached after LLM generation. Stopping further processing.")
                    raise TimeoutError                
            
            if pool is not None:
                pool.close()
                pool.join()
                pool = None
                woker_manager.force_stop_all()
    
    except KeyboardInterrupt:
        print("")
        log_info("Process interrupted by user. Exiting...")
        if pool is not None:
            pool.stop()
            pool.join()
        woker_manager.force_stop_all()
    
    except TimeoutError:
        print("")
        log_info(f"Time limit of {total_time} reached. Exiting...")
        if pool is not None:
            pool.stop()
            pool.join()
        woker_manager.force_stop_all()
        
    except Exception as e:
        print("")
        logging("ERROR", f"An unexpected error occurred: {e}\n{traceback.format_exc()}")
        if pool is not None:
            pool.stop()
            pool.join()
        woker_manager.force_stop_all()
    
    else:
        if pool is not None:
            pool.stop()
            pool.join()
    log_info(f"{woker_manager.get_fin_num()} test cases processed. Results are in {config.output_dir}.")
    
    compile_failed = woker_manager.get_compile_failed_list()
    if len(compile_failed) > 0:
        log_info(f"The following tests failed before execution({len(compile_failed)}): {compile_failed}")
    else:
        log_info("All tests compiled successfully.")
    
    run_failed = woker_manager.get_run_failed_list()
    if len(run_failed) > 0:
        log_info(f"The following tests failed during execution({len(run_failed)}): {run_failed}")
    else:
        log_info("No tests failed during execution.")
    
    timed_out = woker_manager.get_timed_out_list()
    if len(timed_out) > 0:
        log_info(f"The following tests timed out during execution({len(timed_out)}): {timed_out}")
    else:
        log_info("No tests timed out during execution.")

    shared_diff_record = woker_manager.get_diff_list()
    if len(shared_diff_record) > 0:
        log_info(f"Differences found in the following tests({len(shared_diff_record)}): {shared_diff_record}")
    else:
        log_info("No differences found between executions with and without JIT.")
    
    log_info(f"Average time for preparing each test case: {woker_manager.get_average_prepare_time_s()} seconds.")
    log_info(f"Average time for executing each test case: {woker_manager.get_average_exec_time_s()} seconds.")
    log_info(f"Average time for scoring each test case: {woker_manager.get_average_score_time_s()} seconds.")
    log_info(f"Average time for updating bases each test case: {woker_manager.get_average_update_base_time_s()} seconds.")
    
    woker_manager.out_rec_data()
