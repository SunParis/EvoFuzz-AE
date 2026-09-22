
from typing import Tuple

from base.seed_base import seed_generator
from utils.fs import Message
from utils.log import *
from utils.fs import *
from utils.cmd import run_cmd
from utils.log import Message

class JavaFuzzerRunner(seed_generator):
    
    def __init__(
        self,
        ruby_path: str,
        java_fuzzer_home: str,
        java_fuzzer_config_path: str,
        out_file_name: str = "Test.java",
        max_try: int = 5
    ):
        self.java_fuzzer_home = java_fuzzer_home
        self.out_file_name = out_file_name
        self.max_try = max_try
        self.java_fuzzer_cmd = f"{ruby_path} -I {path_join(java_fuzzer_home, 'rb')} {path_join(java_fuzzer_home, 'rb', 'Fuzzer.rb')} -f {java_fuzzer_config_path}"
    
    def get_seed(self, out_dir: str) -> Tuple[bool, str, Message]:
        rc = 0
        out: str = ""
        for idx in range(self.max_try):
            rc, out, err, _ = run_cmd(self.java_fuzzer_cmd, cwd=out_dir)
            if rc == 0:
                break
            log_info(f"Java fuzzer attempt failed with err: {err}. Retrying... ({idx + 1}/{self.max_try})")
        if rc != 0:
            return False, '', Message("ERROR", f"Java fuzzer failed with rc={rc}.")
        write_text(path_join(out_dir, self.out_file_name), out, encoding="utf-8")
        return True, path_join(out_dir, self.out_file_name), Message("INFO", "Java fuzzer completed successfully.")
    
    def get_generator_name(self) -> str:
        return "JavaFuzzer"
    
        
