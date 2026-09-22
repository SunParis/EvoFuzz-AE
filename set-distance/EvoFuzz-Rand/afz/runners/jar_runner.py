
from utils.log import *
from utils.fs import *
from utils.custom_template import *
from utils.cmd import run_cmd

import random
from typing import Tuple

class AllFuzzerJarRunner:
    def __init__(
        self,
        run_java_home: str,
        jar_path: str,
        stmt_cnt: int,
        array_size: int,
        ignore_list_path: str | None,
        timeout: int,
        insert_one_point: str | None,
        insert_in_order: str,
        insert_in_prev_point_iteration: int | None
    ):
        jar_cmd = (
            f'"{path_join(run_java_home, "bin", "java")}" '
            f'-Xms3968m -Xmx3968m -XX:MaxMetaspaceSize=512m '
            f'-XX:MaxDirectMemorySize=256m -XX:ReservedCodeCacheSize=128m '
            f'-Xss256k -XX:+UseG1GC -XX:ParallelGCThreads=1 '
            f'-XX:ConcGCThreads=1 -XX:+ExitOnOutOfMemoryError '
            f'--add-modules jdk.incubator.vector '
            f'-jar "{jar_path}" '
            f'--input-file "%%input_file_path" '
            f'--output-path "%%output_java_path" '
            f'--stmt-cnt %%stmt_cnt '
            f'--arr-size {array_size} '
            f'--data-output-path "%%data_output_path" '
            f'--code-brick-path "%%code_bricks_path" '
            f'--insert-in-one-point "%%insert_one_point" '
            f'--insert-in-order {insert_in_order}'
        )
        if ignore_list_path:
            jar_cmd += f' --ignore-list-path "{ignore_list_path}"'
        self.timeout = timeout
        self.insert_one_point = insert_one_point
        self.run_allfuzzer_cmd = CustomTemplate(jar_cmd)
        self.insert_in_prev_point_iteration = insert_in_prev_point_iteration if insert_in_prev_point_iteration is not None else -1
        self.stmt_cnt = stmt_cnt

    def run(
        self,
        run_dir: str,
        input_file_path: str,
        output_java_path: str,
        data_output_path: str,
        code_bricks_path: str,
        mut_num: int | None = None
    ) -> Tuple[bool, Message]:
        insert_one_point = self.insert_one_point
        if insert_one_point is None:
            insert_one_point = "true" if random.randint(0, 10) > 3 else "false"
            log_info(f"Seed {Path(run_dir).name}: Randomly setting insert_one_point to {insert_one_point}")
        stmt_cnt = mut_num if mut_num is not None else self.stmt_cnt
        cmd = self.run_allfuzzer_cmd.safe_substitute(
            input_file_path=input_file_path,
            output_java_path=output_java_path,
            data_output_path=data_output_path,
            code_bricks_path=code_bricks_path,
            insert_one_point=insert_one_point,
            stmt_cnt=stmt_cnt
        )
        if insert_one_point == "false" and self.insert_in_prev_point_iteration >= 0:
            log_info(f"Seed {Path(run_dir).name}: insert_one_point is false, disabling insert_in_order and setting insert_in_prev_point_iteration")
        elif insert_one_point == "true" and self.insert_in_prev_point_iteration >= 0:
            cnt_prev = count_str(input_file_path, "// AllFuzzer: insert Mutators Here")
            if cnt_prev is not None:
                cmd = cmd + f' --add-insert-flag true --insert-in-prevpoint true'
                if cnt_prev >= self.insert_in_prev_point_iteration:
                    cmd = cmd + f' --clear-insert-flags true'
            else:
                log_info(f"Seed {Path(run_dir).name}: Failed to count occurrences in input file, skipping insert_in_prev_point_iteration logic")
        rc, out, err, _ = run_cmd(cmd, cwd=run_dir, timeout=self.timeout, mem_limit_mb=8*1024)
        log_path = path_join(run_dir, "allfuzzer.generate.log")
        write_text(log_path, f"Output:\n{out}\nError:\n{err}\n", encoding="utf-8")
        ret_msg = Message("INFO", f"AllFuzzer jar execution succeeded.")
        if rc != 0:
            ret_msg = Message("ERROR", f"AllFuzzer jar execution failed rc={rc}.")
        return rc == 0, ret_msg

