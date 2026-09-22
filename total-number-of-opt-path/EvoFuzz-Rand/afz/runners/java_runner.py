from typing import Tuple, List
from enum import Enum

from utils.log import *
from utils.fs import *
from utils.custom_template import *
from utils.cmd import run_cmd

class RunJavaStatus(Enum):
    SUCCESS = 0
    ERROR = 1
    TIMEOUT = 2
    ABORT = 3

class JavaOptLevel(Enum):
    INTERPRETED = 0
    MIXED = 1
    JIT_COMPILED = 2
    JIT_COMPCOMPILED = 3

class JavaRunner:
    
    def __init__(self,
        java_home: str,
        verified_java_home: str,
        verified_openjdk_home: str | None,
        args: str,
        timeout: int,
        memory_limit_mb: int,
        libs: list[str] | None = None
    ):
        self.java_home = java_home
        self.verified_java_home = verified_java_home
        self.args = args
        self.timeout = timeout
        self.memory_limit_mb = memory_limit_mb
        self.is_graalvm = _is_graalvm(self.java_home)
        self.is_opj9 = _is_openj9(self.java_home)
        self.is_vf_opj9 = _is_openj9(self.verified_java_home)
        self.is_debug, self.args_candidate = _is_debug(self.java_home, self.is_opj9)
        
        j_libs: str = ''
        if libs is not None and len(libs) > 0:
            j_libs = ':'.join(libs)
            j_libs = f"-sourcepath {j_libs}"
        self.javac_cmd = CustomTemplate(
            f'"{path_join(self.java_home, "bin", "javac")}" {j_libs} -J-Xms2g -J-Xmx4g '
            f'-J-XX:MaxMetaspaceSize=256m -J-XX:MaxDirectMemorySize=64m '
            f'-J-Xss256k -J-XX:ParallelGCThreads=1 '
            f'-J-XX:ReservedCodeCacheSize=64m -J-Xss256k -J-XX:+UseSerialGC '
            f'-J-XX:ActiveProcessorCount=1 -J-XX:+ExitOnOutOfMemoryError '
            f'{'-J-Xshareclasses:none ' if self.is_opj9 else ''}'
            f'-d . -implicit:class %%args %%file_path'
        )
        
        if verified_java_home != java_home:
            self.verified_javac_cmd = CustomTemplate(
                f'"{path_join(verified_java_home, "bin", "javac")}" {j_libs} -J-Xms2g -J-Xmx4g '
                f'-J-XX:MaxMetaspaceSize=256m -J-XX:MaxDirectMemorySize=64m '
                f'-J-Xss256k -J-XX:ParallelGCThreads=1 '
                f'-J-XX:ReservedCodeCacheSize=64m -J-Xss256k -J-XX:+UseSerialGC '
                f'-J-XX:ActiveProcessorCount=1 -J-XX:+ExitOnOutOfMemoryError '
                f'{'-J-Xshareclasses:none ' if self.is_vf_opj9 else ''}'
                f'-d . -implicit:class %%args %%file_path'
            )
        else:
            self.verified_javac_cmd = self.javac_cmd
        
        if verified_openjdk_home is not None and verified_openjdk_home != verified_java_home:
            self.openjdk_compile_cmd = CustomTemplate(
                f'"{path_join(verified_openjdk_home, "bin", "javac")}" {j_libs} -J-Xms2g -J-Xmx4g '
                f'-J-XX:MaxMetaspaceSize=256m -J-XX:MaxDirectMemorySize=64m '
                f'-J-Xss256k -J-XX:ParallelGCThreads=1 '
                f'-J-XX:ReservedCodeCacheSize=64m -J-Xss256k -J-XX:+UseSerialGC '
                f'-J-XX:ActiveProcessorCount=1 -J-XX:+ExitOnOutOfMemoryError '
                f'-d . -implicit:class %%args %%file_path'
            )
            self.verified_openjdk_cmd = CustomTemplate(
                f'"{path_join(verified_openjdk_home, "bin", "java")}" '
                f'%%args -Dtest.jdk={verified_openjdk_home} -Djdk.test.lib.random.seed=1 '
                f'-cp %%work_dir %%clz_path'
            )
        else:
            self.openjdk_compile_cmd = None
            self.verified_openjdk_cmd = None
    
    def is_openj9(self) -> bool:
        return self.is_opj9
    
    @staticmethod
    def _make_args(args: str, with_jit: bool) -> str:
        args_list: List[str] = args.split()
        ret: str = ''
        has_xlog = False
        
        if with_jit:
            for arg in args_list:
                # if arg.strip().startswith("-XX:+UnlockDiagnosticVMOptions") or \
                #    arg.strip().startswith("-XX:+LogCompilation") or \
                #    arg.strip().startswith("-XX:LogFile=") or \
                if arg.strip().startswith("-Xcomp") or \
                   arg.strip().startswith("-Xint") or \
                   arg.strip().startswith("-Xmixed") or \
                   arg.strip().startswith("-Xbatch") or \
                   len(arg.strip()) == 0:
                    continue
                if arg.strip().startswith("-Xlog"):
                    has_xlog = True
                    continue
                ret += arg + ' '
            
            # ret = "-XX:+UnlockDiagnosticVMOptions " + ret
            # ret += "-XX:+LogCompilation "
            # ret += "-XX:LogFile=./jit.optimization.log"
            if has_xlog:
                ret += " -Xlog:all=off"
        else:
            for arg in args_list:
                if arg.strip().startswith("-Xcomp") or \
                   arg.strip().startswith("-Xint") or \
                   arg.strip().startswith("-Xmixed") or \
                   arg.strip().startswith("-Xbatch") or \
                   len(arg.strip()) == 0:
                    continue
                if arg.strip().startswith("-Xlog"):
                    has_xlog = True
                    continue
                ret += arg + ' '
            if has_xlog:
                ret += " -Xlog:all=off"
        return ret.strip()

    def _make_java_cmd(self, args: str, work_dir: str, clz_path: str, is_verified: bool = False, opt_lv: JavaOptLevel = JavaOptLevel.MIXED) -> Tuple[str, str]:
        java_home = self.verified_java_home if is_verified else self.java_home
        opt_lv = JavaOptLevel.INTERPRETED if is_verified else opt_lv
        
        tag = '-Xmixed'
        ret2 = 'with `Xmixed`'
        if opt_lv == JavaOptLevel.INTERPRETED:
            tag = '-Xint'
            ret2 = 'with `Xint`'
        elif opt_lv == JavaOptLevel.MIXED:
            if self.is_openj9():
                tag = '-Xjit:optLevel=hot'
                ret2 = 'with `Xjit-hot`'
            else:
                tag = '-Xmixed'
                ret2 = 'with `Xmixed`'
        elif opt_lv == JavaOptLevel.JIT_COMPILED:
            if self.is_openj9():
                tag = '-Xjit:count=200,optLevel=scorching'
                ret2 = 'with `Xjit-200-scorching`'
            else:
                tag = '-Xcomp'
                ret2 = 'with `Xcomp`'
        elif opt_lv == JavaOptLevel.JIT_COMPCOMPILED:
            if self.is_openj9():
                tag = '-Xjit:count=0,optLevel=scorching'
                ret2 = 'with `Xjit-0-scorching`'
            else:
                tag = '-Xcomp'
                ret2 = 'with `Xcomp`'
        
        if self.is_graalvm and not is_verified:
            # For GraalVM, the below options will turn on the `debug` mode of Graal, 
            # which is helpful for fuzzing as it can generate more logs and is more stable.
            tag = f'-ea -esa -XX:+UseJVMCICompiler -Dgraal.CompilationFailureAction=ExitVM {tag}'
        elif self.is_graalvm:
            tag = f'-ea -esa {tag}'
            
        args = self._make_args(args, opt_lv != JavaOptLevel.INTERPRETED and opt_lv != JavaOptLevel.MIXED) 
        args += ' -Xshareclasses:none' if (self.is_opj9 and not is_verified) or \
            (self.is_vf_opj9 and is_verified) else ''   
        args = f"{tag} {args}".strip()
        
        return f"{java_home}/bin/java {args} -Dtest.jdk={self.java_home} -Djdk.test.lib.random.seed=1 -cp {work_dir} {clz_path}", ret2
        
    def compile(
        self,
        run_dir: str,
        file_path: str,
        std_out_path: str,
        err_out_path: str,
        extra_args: str = "",
        verified: bool = False
    ) -> Tuple[bool, Message]:
        """
        Compile Java code and write:
          - compile.log
          - compile.err
        """
        true_javac_cmd = self.verified_javac_cmd if verified else self.javac_cmd
        compile_cmd = true_javac_cmd.safe_substitute(args=self.args + " " + extra_args, file_path=file_path)
        rc, out, err, _ = run_cmd(
            compile_cmd,
            cwd=run_dir, timeout=self.timeout, mem_limit_mb=self.memory_limit_mb
        )
        out = f'Java Compilation Command:\n  {compile_cmd}\n\n{out}'
        if std_out_path is not None and out is not None and out.strip() != "":
            write_text(std_out_path, out, encoding="utf-8")
        if err_out_path is not None and err is not None and err.strip() != "":
            write_text(err_out_path, err, encoding="utf-8")

        if rc != 0:
            return False, Message("ERROR", f"Compilation failed rc={rc}.")
        return True, Message("INFO", f"Compilation succeeded.")
    
    def run(
        self,
        run_dir: str,
        std_out_path: str,
        err_out_path: str,
        clz_path: str,
        extra_args: str = "",
        verified: bool = False,
        opt_lv: JavaOptLevel = JavaOptLevel.MIXED
    ) -> Tuple[RunJavaStatus, Message, Tuple[str, str]]:
        """
        Run Java with or without JIT and write:
          - *.log
          - *.err
        """
        curr_idx: str = Path(run_dir).name
        opt_lv = JavaOptLevel.INTERPRETED if verified else opt_lv
        if not verified:
            extra_args = _add_showy_args(extra_args, self.args_candidate, self.is_debug, self.is_opj9)
        cmd, with_jit_str = self._make_java_cmd(self.args + " " + extra_args, run_dir, clz_path, verified, opt_lv)
        
        log_debug(f"Start running a java cmd under dir: {run_dir}.")
        rc, out_, err_, duration = run_cmd(cmd, cwd=run_dir, timeout=self.timeout, mem_limit_mb=self.memory_limit_mb)
        time_line = f"\n{with_jit_str} processing time: {duration if duration > 0 else self.timeout} seconds\n"
        log_debug(f"Finish running a java cmd under dir: {run_dir}.")
        
        # Cap output to 150MiB
        out = write_capped(out_)
        err = write_capped(err_)
        if len(out_) > len(out):
            logging('WARNING', f'Seed {curr_idx}: stdout truncated from {len(out_)} to {len(out)} bytes.')
        if len(err_) > len(err):
            logging('WARNING', f'Seed {curr_idx}: stderr truncated from {len(err_)} to {len(err)} bytes.')
        
        log_debug(f"Writing outputs to files under dir: {run_dir}.")
        write_text(std_out_path, f'Java Execution Command:\n  {cmd}\n\n{out}\n\n{time_line}', encoding="utf-8")
        write_text(err_out_path, err, encoding="utf-8")
        log_debug(f"Finished writing outputs to files under dir: {run_dir}.")
                
        ret_status = RunJavaStatus.SUCCESS
        ret_msg = None
        if err.strip().find("Aborted") != -1 or \
            out.strip().find("bug report") != -1:
            ret_status = RunJavaStatus.ABORT
            ret_msg = Message("INFO", f"Execution {with_jit_str} failed (aborted), see {std_out_path} for details.")
        elif self.is_opj9 and \
            (out.strip().find("JVMDUMP") != -1 or \
            err.strip().find("JVMDUMP") != -1):
            # For OpenJ9, Aborted may not appear, but JVMDUMP indicates a crash
            ret_status = RunJavaStatus.ABORT
            ret_msg = Message("INFO", f"OpenJ9 execution failed (aborted), see {std_out_path} for details.")
        elif self.is_graalvm and \
            (out.strip().find("JVMDUMP") != -1 or \
            err.strip().find("JVMDUMP") != -1 or \
            out.strip().find("Graal compilation failure") != -1 or \
            err.strip().find("Graal compilation failure") != -1):
            ret_status = RunJavaStatus.ABORT
            ret_msg = Message("INFO", f"GraalVM execution failed (aborted), see {std_out_path} for details.")
        elif duration < 0:
            ret_status = RunJavaStatus.TIMEOUT
            ret_msg = Message("WARNING", f"Execution {with_jit_str} timed out after {self.timeout} seconds, see {std_out_path} for details.")
        elif rc != 0:
            ret_status = RunJavaStatus.ERROR
            if out.strip().find("OutOfMemoryError") != -1:
                ret_msg = Message("ERROR", f"Execution {with_jit_str} failed due to OutOfMemoryError, see {err_out_path} for details.")
            else:
                ret_msg = Message("ERROR", f"Execution {with_jit_str} failed with rc={rc}, see {err_out_path} for details.")
        if ret_msg is None:
            ret_msg = Message("INFO", f"Execution {with_jit_str} successful ({duration} seconds), see {std_out_path} for details.")
        
        log_debug(f"Start extracting true outputs under dir: {run_dir}.")
        
        true_out, true_err = '', ''
        try:
            with time_limit(30):
                log_debug(f"Extracting stdout outputs under dir: {run_dir}.")
                for line in (out or '').splitlines():
                    if line.strip().startswith("TestDigest"):
                        continue
                    if line.strip().startswith("Warning:"):
                        continue
                    if line.strip().startswith("WARNING:"):
                        continue
                    if line.strip().startswith("Picked up JAVA_TOOL_OPTIONS"):
                        continue
                    if line.strip().startswith("Picked up _JAVA_OPTIONS"):
                        continue
                    true_out += line + '\n'
                log_debug(f"Extracting stderr outputs under dir: {run_dir}.")
                for line in (err or '').splitlines():
                    if line.strip().startswith("TestDigest"):
                        continue
                    if line.strip().startswith("Warning:"):
                        continue
                    if line.strip().startswith("WARNING:"):
                        continue
                    if line.strip().startswith("Picked up JAVA_TOOL_OPTIONS"):
                        continue
                    if line.strip().startswith("Picked up _JAVA_OPTIONS"):
                        continue
                    true_err += line + '\n'
                log_debug(f"Finished extracting true outputs under dir: {run_dir}.")
        except Exception:
            logging('WARNING', f'Seed {curr_idx}: Extracting output timed out. Setting out and err to empty strings.')
            true_out, true_err = '', ''
            
        # Remove *.xml to reduce disk usage, as they can be very large and are not needed after extraction
        if extra_args.find("PrintIdealGraphFile") != -1:
            for fls in list_all_files_abs(run_dir, '.xml'):
                if fls.find('allfuzzer_test_') != -1:
                    rm_succ = safe_remove(fls)
                    if not rm_succ:
                        logging('WARNING', f'Seed {curr_idx}: Failed to remove intermediate file {fls}.')
                    else:
                        log_debug(f"Removed intermediate file {fls} under dir: {run_dir}.")
        
        log_debug(f"Finished extracting true outputs under dir: {run_dir}.")
        return ret_status, ret_msg, (true_out, true_err)
    
    def compile_verified_openjdk(
        self,
        run_dir: str,
        file_path: str,
        std_out_path: str,
        err_out_path: str,
        extra_args: str = ""
    ) -> Tuple[bool, Message]:
        """
        Compile Java code with verified OpenJDK and write:
          - compile.log
          - compile.err
        """
        if self.openjdk_compile_cmd is None:
            return False, Message("ERROR", "verified_openjdk_home not set, cannot compile with verified OpenJDK.")
        
        compile_cmd = self.openjdk_compile_cmd.safe_substitute(args=self.args + " " + extra_args, file_path=file_path)
        rc, out, err, _ = run_cmd(
            compile_cmd,
            cwd=run_dir, timeout=self.timeout, mem_limit_mb=self.memory_limit_mb
        )
        out = f'OpenJDK Compilation Command:\n  {compile_cmd}\n\n{out}'
        if std_out_path is not None and out is not None and out.strip() != "":
            write_text(std_out_path, out, encoding="utf-8")
        if err_out_path is not None and err is not None and err.strip() != "":
            write_text(err_out_path, err, encoding="utf-8")

        if rc != 0:
            return False, Message("ERROR", f"OpenJDK Compilation failed rc={rc}.")
        return True, Message("INFO", f"OpenJDK Compilation succeeded.")
    
    def run_verified_openjdk(
        self,
        run_dir: str,
        std_out_path: str,
        err_out_path: str,
        clz_path: str,
        extra_args: str = ""
    ) -> Tuple[RunJavaStatus, Message, Tuple[str, str]]:
        """
        Run Java with verified OpenJDK and write:
          - *.log
          - *.err
        """
        if self.verified_openjdk_cmd is None:
            return RunJavaStatus.ERROR, Message("ERROR", "verified_openjdk_home not set, cannot run with verified OpenJDK."), ("", "")
        
        cmd = self.verified_openjdk_cmd.safe_substitute(args=self.args + " " + extra_args, work_dir=run_dir, clz_path=clz_path)
        
        log_debug(f"Start running verified OpenJDK cmd under dir: {run_dir}.")
        rc, out_, err_, duration = run_cmd(cmd, cwd=run_dir, timeout=self.timeout, mem_limit_mb=self.memory_limit_mb)
        time_line = f"\nOpenJDK processing time: {duration if duration > 0 else self.timeout} seconds\n"
        log_debug(f"Finish running verified OpenJDK cmd under dir: {run_dir}.")
        
        # Cap output to 150MiB
        out = write_capped(out_)
        err = write_capped(err_)
        if len(out_) > len(out):
            logging('WARNING', f'OpenJDK stdout truncated from {len(out_)} to {len(out)} bytes.')
        if len(err_) > len(err):
            logging('WARNING', f'OpenJDK stderr truncated from {len(err_)} to {len(err)} bytes.')
        
        log_debug(f"Writing verified OpenJDK outputs to files under dir: {run_dir}.")
        write_text(std_out_path, f'OpenJDK Execution Command:\n  {cmd}\n\n{out}\n\n{time_line}', encoding="utf-8")
        write_text(err_out_path, err, encoding="utf-8")
        log_debug(f"Finished writing verified OpenJDK outputs to files under dir: {run_dir}.")
        
        ret_status = RunJavaStatus.SUCCESS
        ret_msg = None
        if err.strip().find("Aborted") != -1 or \
            out.strip().find("bug report") != -1:
            ret_status = RunJavaStatus.ABORT
            ret_msg = Message("INFO", f"OpenJDK execution failed (aborted), see {std_out_path} for details.")
        elif self.is_opj9 and \
            (out.strip().find("JVMDUMP") != -1 or \
            err.strip().find("JVMDUMP") != -1):
            # For OpenJ9, Aborted may not appear, but JVMDUMP indicates a crash
            ret_status = RunJavaStatus.ABORT
            ret_msg = Message("INFO", f"OpenJ9 execution failed (aborted), see {std_out_path} for details.")
        elif self.is_graalvm and \
            (out.strip().find("JVMDUMP") != -1 or \
            err.strip().find("JVMDUMP") != -1 or \
            out.strip().find("Graal compilation failure") != -1 or \
            err.strip().find("Graal compilation failure") != -1):
            ret_status = RunJavaStatus.ABORT
            ret_msg = Message("INFO", f"GraalVM execution failed (aborted), see {std_out_path} for details.")
        elif duration < 0:
            ret_status = RunJavaStatus.TIMEOUT
            ret_msg = Message("WARNING", f"OpenJDK execution timed out after {self.timeout} seconds, see {std_out_path} for details.")
        elif rc != 0:
            ret_status = RunJavaStatus.ERROR
            if out.strip().find("OutOfMemoryError") != -1:
                ret_msg = Message("ERROR", f"OpenJDK execution failed due to OutOfMemoryError, see {err_out_path} for details.")
            else:
                ret_msg = Message("ERROR", f"OpenJDK execution failed with rc={rc}, see {err_out_path} for details.")
        if ret_msg is None:
            ret_msg = Message("INFO", f"OpenJDK execution successful ({duration} seconds), see {std_out_path} for details.")
        log_debug(f"Start extracting true OpenJDK outputs under dir: {run_dir}.")
        true_out, true_err = '', ''
        try:
            with time_limit(30):
                log_debug(f"Extracting OpenJDK stdout outputs under dir: {run_dir}.")
                for line in (out or '').splitlines():
                    if line.strip().startswith("TestDigest"):
                        continue
                    if line.strip().startswith("Warning:"):
                        continue
                    if line.strip().startswith("WARNING:"):
                        continue
                    if line.strip().startswith("Picked up JAVA_TOOL_OPTIONS"):
                        continue
                    if line.strip().startswith("Picked up _JAVA_OPTIONS"):
                        continue
                    true_out += line + '\n'
                log_debug(f"Extracting OpenJDK stderr outputs under dir: {run_dir}.")
                for line in (err or '').splitlines():
                    if line.strip().startswith("TestDigest"):
                        continue
                    if line.strip().startswith("Warning:"):
                        continue
                    if line.strip().startswith("WARNING:"):
                        continue
                    if line.strip().startswith("Picked up JAVA_TOOL_OPTIONS"):
                        continue
                    if line.strip().startswith("Picked up _JAVA_OPTIONS"):
                        continue
                    true_err += line + '\n'
                log_debug(f"Finished extracting true OpenJDK outputs under dir: {run_dir}.")
        except Exception:
            logging('WARNING', f'Extracting OpenJDK output timed out. Setting out and err to empty strings.')
            true_out, true_err = '', ''
        log_debug(f"Finished extracting true OpenJDK outputs under dir: {run_dir}.")
        return ret_status, ret_msg, (true_out, true_err)
    
    def same_java_home(self) -> bool:
        return self.java_home == self.verified_java_home

def _add_showy_args(org_args: str, support_aggs: str, is_debug: bool = True, is_j9: bool = True) -> str:
    if is_j9:
        # Not support for J9 yet
        return org_args
    import random
    # xml_name = 'allfuzzer_test_' + str(time.clock_gettime_ns(time.CLOCK_MONOTONIC))
    args_list = [
        # ['Can run in release', 'Key word', 'Candidate 1', 'Candidate 2', ...]
        [True, "CompileThreshold", "-XX:CompileThreshold=32", "-XX:CompileThreshold=64"],
        [True, "TieredCompilation", "-XX:+TieredCompilation", "-XX:-TieredCompilation"],
        [True, "IncrementalInlineVirtual", "-XX:+IncrementalInlineVirtual", "-XX:-IncrementalInlineVirtual"],
        [True, "IncrementalInlineMH", "-XX:+IncrementalInlineMH", "-XX:-IncrementalInlineMH"],
        [True, "UseInlineCaches", "-XX:+UseInlineCaches", "-XX:-UseInlineCaches"],
        # [False, "PrintIdealGraph",
        #     f"-XX:+PrintIdealGraph -XX:PrintIdealGraphLevel=1 -XX:PrintIdealGraphFile=./{xml_name}.xml",
        #     f"-XX:+PrintIdealGraph -XX:PrintIdealGraphLevel=2 -XX:PrintIdealGraphFile=./{xml_name}.xml"]
    ]
    showy_args = ""
    add_sth = False
    for agg in args_list:
        if (agg[0] or is_debug) and \
           support_aggs.find(agg[1]) != -1 and \
           org_args.find(agg[2]) == -1 and \
           random.choice([False, False, True]):
            add_sth = True
            showy_args += " " + agg[random.choice(range(3, len(agg)))]
    if add_sth and showy_args.find('UnlockDiagnosticVMOptions') == -1 \
       and org_args.strip().find('UnlockDiagnosticVMOptions') == -1:
        showy_args = f'-XX:+UnlockDiagnosticVMOptions {showy_args}'
    return org_args.strip() + " " + showy_args.strip()

def _is_openj9(verified_java_home: str) -> bool:
    java_bin = path_join(verified_java_home, "bin", "java")
    rc, out, err, _ = run_cmd(f'"{java_bin}" -Xshareclasses:none -version', verified_java_home, timeout=300)
    if rc != 0:
        return False
    if out.find("OpenJ9") != -1 or err.find("OpenJ9") != -1:
        return True
    return False

def _is_graalvm(verified_java_home: str) -> bool:
    java_bin = path_join(verified_java_home, "bin", "java")
    rc, out, err, _ = run_cmd(f'"{java_bin}" -version', verified_java_home, timeout=300)
    if rc != 0:
        return False
    if out.find("GraalVM") != -1 or err.find("GraalVM") != -1:
        return True
    return False

def _is_debug(verified_java_home: str, is_j9: bool = False) -> Tuple[bool, str]:
    java_bin = path_join(verified_java_home, "bin", "java")
    rc, out, err, _ = run_cmd(f'"{java_bin}" {'-Xshareclasses:none 'if is_j9 else '-XX:+PrintFlagsFinal '}-version', verified_java_home, timeout=300)
    if rc != 0:
        return False, ""
    if out.find("debug") != -1 or err.find("debug") != -1:
        return True, out.strip()
    return False, out.strip()
