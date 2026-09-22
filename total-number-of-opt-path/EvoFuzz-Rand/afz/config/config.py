import os
import shutil
import yaml
from typing import Any, Optional, List

from seed_mut_selector.config import *
from utils.log import *
from utils.fs import *

class Config:
    config_file: str
    run_java_home: str
    additional_java_lib_paths: List[str] | None
    test_java_home: str
    verified_java_home: str
    verified_openjdk_home: str | None
    save_success: bool
    output_dir: str
    project_home: str
    total_test_time: int
    timed_out: int
    test_memory_limit: int
    test_java_args: str
    total_test_num: int
    threads: int
    allfuzzer: 'Config.AllFuzzerConfig'
    seed_gen_config: 'Config.SeedGenConfig'
    mut_seed_sel_config: SeedMutSelConfig
    llm: Optional['Config.LLMConfig']
    code_coverage: Optional['Config.CodeCoverageConfig']
    path_coverage: Optional['Config.PathCoverageConfig']
    skip_bug_detection: bool
        
    class AllFuzzerConfig:
        jar_path: str
        code_bricks_dir: str
        ignore_list_path: Optional[str]
        max_statements: int
        max_array_length: int
        insert_in_order: str
        insert_in_one_point: Optional[str]
        insert_in_prev_point_iteration: int
            
    class SeedGenConfig:
        java_fuzzer: Optional['Config.SeedGenConfig.JavaFuzzerConfig']
        jtreg_test: Optional['Config.SeedGenConfig.JtregTestConfig']
        possiblity_of_new_seed: float
        
        class JavaFuzzerConfig:
            ruby_path: str
            java_fuzzer_dir: str
            config_path: str
            java_fuzzer_lib: List[str]
            priority: int
        
        class JtregTestConfig:
            jtreg_test_home: str
            seed_list: str
            jtreg_lib: List[str]
            priority: int
        
    class LLMConfig:
        api_key: str
        base_url: str
        model: str
        timed_out: int
        generate_after_mutation_num: int
    
    class CodeCoverageConfig:
        lcov_path: str
        gcov_path: str
        target_dir: str
        timed_out: int
    
    class PathCoverageConfig:
        test_java_home: str
        data_base_path: str
        focus_files: List[str]
        timed_out: int
        run_args: str

def _set_gb_fields(data: Any, config: Config) -> bool:
    required_fields = [
        "run_java_home",
        "test_java_home",
        "save_success",
        "output_dir",
        "allfuzzer",
        "total_test_time"
    ]
    for field in required_fields:
        if field not in data:
            logging("ERROR", f"Missing required field: {field}")
            return False
        
    config.run_java_home = str(data["run_java_home"])
    if not exists(config.run_java_home):
        logging("ERROR", f"run_java_home path does not exist or is not a directory: {config.run_java_home}")
        return False
    
    config.test_java_home = str(data["test_java_home"])
    if not exists(config.test_java_home):
        logging("ERROR", f"test_java_home path does not exist or is not a directory: {config.test_java_home}")
        return False
    
    config.save_success = bool(data["save_success"])
    
    config.output_dir = str(data["output_dir"])
    if exists(config.output_dir):
        logging("ERROR", f"output_dir already exists: {config.output_dir}")
        return False
    
    # Set total_test_time in seconds
    def get_sec(data: Any) -> int:
        ttime = data["total_test_time"]
        days = ttime.get("days", 0)
        hours = ttime.get("hours", 0)
        minutes = ttime.get("minutes", 0)
        total_seconds = days * 86400 + hours * 3600 + minutes * 60
        return total_seconds
    config.total_test_time = get_sec(data)
    log_info(f"Total test time set to {config.total_test_time} seconds")
    
    if "project_home" in data:
        config.project_home = str(data["project_home"])
    else:
        config.project_home = os.path.abspath(os.path.join(os.path.dirname(__file__), ".."))
        log_info(f"project_home not set, using default {config.project_home}")
    
    if "timed_out" in data:
        config.timed_out = int(data["timed_out"])
    else:
        config.timed_out = 300
        log_info("timed_out not set, using default 300")
        
    if "test_memory_limit" in data:
        config.test_memory_limit = int(data["test_memory_limit"])
    else:
        config.test_memory_limit = -1
        log_info("test_memory_limit not set, using default -1 (no limit)")
    
    if "test_java_args" in data:
        config.test_java_args = str(data["test_java_args"])
    else:
        config.test_java_args = ""
        log_info("test_java_args not set, using default '' (no additional args)")
    
    if "total_test_num" in data:
        config.total_test_num = int(data["total_test_num"])
    else:
        config.total_test_num = -1
        log_info("total_test_num not set, using default -1 (run until time limit)")
    
    if "threads" in data:
        config.threads = int(data["threads"])
    else:
        config.threads = 4
        log_info("threads not set, using default 4")
        
    if "verified_java_home" in data and exists(str(data["verified_java_home"])):
        config.verified_java_home = str(data["verified_java_home"])
    else:
        config.verified_java_home = config.test_java_home
        log_info(f"verified_java_home not set, using test_java_home: {config.verified_java_home}")
            
    if "verified_openjdk_home" in data and exists(str(data["verified_openjdk_home"])):
        config.verified_openjdk_home = str(data["verified_openjdk_home"])
    else:
        config.verified_openjdk_home = None
        log_info(f"verified_openjdk_home not set, OpenJDK verification features will be disabled")
    
    if "additional_java_lib_paths" in data and len(data["additional_java_lib_paths"]) > 0:
        add_lib: str = str(data["additional_java_lib_paths"])
        add_lib_l = [path.strip() for path in add_lib.split(":")]
        config.additional_java_lib_paths = None
        for path in add_lib_l:
            if not exists(path):
                logging("WARNING", f"additional_java_lib_path does not exist: {path}")
            else:
                if config.additional_java_lib_paths is None:
                    config.additional_java_lib_paths = []
                config.additional_java_lib_paths.append(path)
        log_info(f"additional_java_lib_paths set to: {config.additional_java_lib_paths}")
    else:
        config.additional_java_lib_paths = None
        log_info("additional_java_lib_paths not set, no additional Java library paths will be used")
    
    if "skip_bug_detection" in data:
        config.skip_bug_detection = str(data["skip_bug_detection"]).lower() == "true"
        log_info(f"skip_bug_detection set to: {config.skip_bug_detection}")
    else:
        config.skip_bug_detection = False
        log_info("skip_bug_detection not set, using default False")
        
    return True

def _set_allfuzzer_fields(all_fuzzer_data: Any | None, config: Config) -> bool:
    if all_fuzzer_data is None:
        logging("ERROR", "Missing required field: allfuzzer")
        return False
    
    config.allfuzzer = Config.AllFuzzerConfig()
    
    if "jar_path" not in all_fuzzer_data:
        logging("ERROR", "Missing required allfuzzer field: jar_path")
        return False
    elif not exists(str(all_fuzzer_data["jar_path"])):
        logging("ERROR", f"allfuzzer.jar_path does not exist: {all_fuzzer_data['jar_path']}")
        return False
    config.allfuzzer.jar_path = str(all_fuzzer_data["jar_path"])
    
    if "code_bricks_dir" not in all_fuzzer_data:
        logging("ERROR", "Missing required allfuzzer field: code_bricks_dir")
        return False
    elif not exists(str(all_fuzzer_data["code_bricks_dir"])):
        logging("ERROR", f"allfuzzer.code_bricks_dir does not exist: {all_fuzzer_data['code_bricks_dir']}")
        return False
    config.allfuzzer.code_bricks_dir = str(all_fuzzer_data["code_bricks_dir"])
    
    if "ignore_list_path" in all_fuzzer_data:
        config.allfuzzer.ignore_list_path = str(all_fuzzer_data["ignore_list_path"])
        if not exists(config.allfuzzer.ignore_list_path):
            logging("WARNING", f"allfuzzer.ignore_list_path does not exist: {config.allfuzzer.ignore_list_path}")
            config.allfuzzer.ignore_list_path = None
    else:
        config.allfuzzer.ignore_list_path = None
        log_info("allfuzzer.ignore_list_path not set, no ignore list will be used")
    
    if "max_statements" not in all_fuzzer_data:
        logging("ERROR", "Missing required allfuzzer field: max_statements")
        return False
    config.allfuzzer.max_statements = int(all_fuzzer_data["max_statements"])
    
    if "max_array_length" not in all_fuzzer_data:
        logging("ERROR", "Missing required allfuzzer field: max_array_length")
        return False
    config.allfuzzer.max_array_length = int(all_fuzzer_data["max_array_length"])
    
    if "insert_in_order" in all_fuzzer_data:
        config.allfuzzer.insert_in_order = str(all_fuzzer_data["insert_in_order"]).lower()
        if config.allfuzzer.insert_in_order not in ["true", "false"]:
            logging("WARNING", "allfuzzer.insert_in_order must be 'true' or 'false', using default 'false'")
            config.allfuzzer.insert_in_order = "false"
    else:
        config.allfuzzer.insert_in_order = "false"
        log_info("allfuzzer.insert_in_order not set, using default 'false'")
    
    if "insert_in_one_point" in all_fuzzer_data:
        config.allfuzzer.insert_in_one_point = str(all_fuzzer_data["insert_in_one_point"]).lower()
        if config.allfuzzer.insert_in_one_point not in ["true", "false"]:
            logging("WARNING", "allfuzzer.insert_in_one_point must be 'true' or 'false', using random determination")
            config.allfuzzer.insert_in_one_point = None
    else:
        config.allfuzzer.insert_in_one_point = None
        log_info("allfuzzer.insert_in_one_point not set, will be randomly determined")
    
    if "insert_in_prev_point_iteration" in all_fuzzer_data:
        ssstr = str(all_fuzzer_data["insert_in_prev_point_iteration"])
        try:
            config.allfuzzer.insert_in_prev_point_iteration = int(ssstr)
        except ValueError:
            logging("WARNING", "allfuzzer.insert_in_prev_point_iteration must be an integer, using default -1")
            config.allfuzzer.insert_in_prev_point_iteration = -1
    else:
        config.allfuzzer.insert_in_prev_point_iteration = -1
        log_info("allfuzzer.insert_in_prev_point_iteration not set, using default -1 (disabled)")

    return True

def _set_seed_gen_fields(seed_gen_config: Any | None, config: Config) -> bool:
    if seed_gen_config is None:
        logging("ERROR", "Missing required field: seed_generator")
        return False
    
    config.seed_gen_config = Config.SeedGenConfig()
    
    if 'java_fuzzer' not in seed_gen_config and 'jtreg_test' not in seed_gen_config:
        logging("WARNING", "No seed generator configured in seed_generator, no new seeds will be generated")
    if 'possiblity_of_new_seed' not in seed_gen_config:
        logging("WARNING", "Missing optional field: seed_generator.new_seed_generator.possiblity_of_new_seed, using default 0.3")
        config.seed_gen_config.possiblity_of_new_seed = 0.3
    else:
        config.seed_gen_config.possiblity_of_new_seed = float(seed_gen_config['possiblity_of_new_seed'])
        if not (0.0 <= config.seed_gen_config.possiblity_of_new_seed <= 1.0):
            logging("WARNING", "seed_generator.new_seed_generator.possiblity_of_new_seed must be between 0.0 and 1.0, using default 0.3")
            config.seed_gen_config.possiblity_of_new_seed = 0.3
    
    has_seed: bool = False
    if config.seed_gen_config.possiblity_of_new_seed <= 0.0:
        logging("WARNING", "possiblity_of_new_seed is set to 0.0, no new seeds will be generated")
        has_seed = True
    
    if 'java_fuzzer' in seed_gen_config:
        java_fuzzer_data = seed_gen_config['java_fuzzer']
        if java_fuzzer_data is None:
            logging("WARNING", "Missing optional field: seed_generator.new_seed_generator.java_fuzzer")
        elif 'ruby_path' not in java_fuzzer_data:
            logging("WARNING", "Missing optional field: seed_generator.new_seed_generator.java_fuzzer.ruby_path")
        elif not exists(str(java_fuzzer_data['ruby_path'])):
            logging("WARNING", f"seed_generator.java_fuzzer.ruby_path does not exist: {java_fuzzer_data['ruby_path']}")
        elif 'java_fuzzer_dir' not in java_fuzzer_data:
            logging("WARNING", "Missing optional field: seed_generator.new_seed_generator.java_fuzzer.java_fuzzer_dir")
        elif not exists(str(java_fuzzer_data['java_fuzzer_dir'])):
            logging("WARNING", f"seed_generator.java_fuzzer.java_fuzzer_dir does not exist: {java_fuzzer_data['java_fuzzer_dir']}")
        elif 'config_path' not in java_fuzzer_data:
            logging("WARNING", "Missing optional field: seed_generator.new_seed_generator.java_fuzzer.config_path")
        elif not exists(str(java_fuzzer_data['config_path'])):
            logging("WARNING", f"seed_generator.java_fuzzer.config_path does not exist: {java_fuzzer_data['config_path']}")
        else:
            config.seed_gen_config.java_fuzzer = Config.SeedGenConfig.JavaFuzzerConfig()
            config.seed_gen_config.java_fuzzer.ruby_path = str(java_fuzzer_data['ruby_path'])
            config.seed_gen_config.java_fuzzer.java_fuzzer_dir = str(java_fuzzer_data['java_fuzzer_dir'])
            config.seed_gen_config.java_fuzzer.config_path = str(java_fuzzer_data['config_path'])
            config.seed_gen_config.java_fuzzer.java_fuzzer_lib = [path_join(
                config.seed_gen_config.java_fuzzer.java_fuzzer_dir, "lib")]
            has_seed = True
            if 'priority' in java_fuzzer_data:
                config.seed_gen_config.java_fuzzer.priority = int(java_fuzzer_data['priority'])
            else:
                config.seed_gen_config.java_fuzzer.priority = 50
                log_info("seed_generator.java_fuzzer.priority not set, using default 50")
    else:
        config.seed_gen_config.java_fuzzer = None
        log_info("seed_generator.java_fuzzer not set, java_fuzzer features will be disabled")
    
    if 'jtreg_test' in seed_gen_config:
        jtreg_test_data = seed_gen_config['jtreg_test']
        if jtreg_test_data is None:
            logging("WARNING", "Missing optional field: seed_generator.jtreg_test")
        elif 'jtreg_test_home' not in jtreg_test_data:
            logging("WARNING", "Missing optional field: seed_generator.jtreg_test.jtreg_test_home")
        elif not exists(str(jtreg_test_data['jtreg_test_home'])):
            logging("WARNING", f"seed_generator.jtreg_test.jtreg_test_home does not exist: {jtreg_test_data['jtreg_test_home']}")
        elif 'seed_list' not in jtreg_test_data:
            logging("WARNING", "Missing optional field: seed_generator.jtreg_test.seed_list")
        elif not exists(str(jtreg_test_data['seed_list'])):
            logging("WARNING", f"seed_generator.jtreg_test.seed_list does not exist: {jtreg_test_data['seed_list']}")
        else:
            config.seed_gen_config.jtreg_test = Config.SeedGenConfig.JtregTestConfig()
            config.seed_gen_config.jtreg_test.jtreg_test_home = str(jtreg_test_data['jtreg_test_home'])
            config.seed_gen_config.jtreg_test.seed_list = str(jtreg_test_data['seed_list'])
            config.seed_gen_config.jtreg_test.jtreg_lib = [
                path_join(config.seed_gen_config.jtreg_test.jtreg_test_home, "jtreg-lib"),
                config.seed_gen_config.jtreg_test.jtreg_test_home
            ]
            has_seed = True
            if 'priority' in jtreg_test_data:
                config.seed_gen_config.jtreg_test.priority = int(jtreg_test_data['priority'])
            else:
                config.seed_gen_config.jtreg_test.priority = 50
                log_info("seed_generator.jtreg_test.priority not set, using default 50")
    else:
        config.seed_gen_config.jtreg_test = None
        log_info("seed_generator.jtreg_test not set, jtreg_test features will be disabled")
    
    if not has_seed:
        logging("ERROR", "No valid seed configuration found in seed_generator")
    return has_seed



def _set_code_coverage_fields(code_coverage_data: Any | None, config: Config) -> bool:
    if code_coverage_data is None:
        logging("WARNING", "Missing optional field: code_coverage")
        return False
    
    config.code_coverage = Config.CodeCoverageConfig()
    
    if "target_dir" not in code_coverage_data:
        logging("WARNING", f"Missing required code_coverage field: code_coverage.target_dir")
        return False
    elif not exists(str(code_coverage_data["target_dir"])):
        logging("WARNING", f"code_coverage.target_dir does not exist: {code_coverage_data['target_dir']}")
        return False
    config.code_coverage.target_dir = str(code_coverage_data["target_dir"])
    
    if "lcov_path" not in code_coverage_data:
        logging("WARNING", f"Missing required code_coverage field: code_coverage.lcov_path")
        lcov_path = shutil.which("lcov")
        if lcov_path is None:
            logging("WARNING", "code_coverage.lcov_path not set and `lcov` not found in PATH, disabling llm features")
            return False
        log_info("code_coverage.lcov_path not set, using `lcov` from PATH")
        config.code_coverage.lcov_path = lcov_path
    else:
        config.code_coverage.lcov_path = str(code_coverage_data["lcov_path"])
    if not exists(config.code_coverage.lcov_path):
        logging("WARNING", f"code_coverage.lcov_path does not exist: {config.code_coverage.lcov_path}")
        return False
    
    if "gcov_path" not in code_coverage_data:
        logging("WARNING", f"Missing required code_coverage field: code_coverage.gcov_path")
        gcov_path = shutil.which("gcov")
        if gcov_path is None:
            logging("WARNING", "code_coverage.gcov_path not set and `gcov` not found in PATH, disabling llm features")
            return False
        log_info("code_coverage.gcov_path not set, using `gcov` from PATH")
        config.code_coverage.gcov_path = gcov_path
    else:
        config.code_coverage.gcov_path = str(code_coverage_data["gcov_path"])
    if not exists(config.code_coverage.gcov_path):
        logging("WARNING", f"code_coverage.gcov_path does not exist: {config.code_coverage.gcov_path}")
        return False
    
    if "timed_out" in code_coverage_data:
        config.code_coverage.timed_out = int(code_coverage_data["timed_out"])
    else:
        config.code_coverage.timed_out = config.timed_out
        log_info("code_coverage.timed_out not set, using test timeout")
    
    return True

def _set_path_coverage_fields(path_coverage_data: Any | None, config: Config) -> bool:
    
    if path_coverage_data is None:
        logging("WARNING", "Missing optional field: path_coverage")
        return False
    
    config.path_coverage = Config.PathCoverageConfig()
    
    if "test_java_home" not in path_coverage_data:
        logging("WARNING", f"Missing required path_coverage field: path_coverage.test_java_home")
        return False
    elif not exists(str(path_coverage_data["test_java_home"])):
        logging("WARNING", f"path_coverage.test_java_home does not exist: {path_coverage_data['test_java_home']}")
        return False
    config.path_coverage.test_java_home = str(path_coverage_data["test_java_home"])
    
    if "data_base_path" not in path_coverage_data:
        logging("WARNING", f"Missing required path_coverage field: path_coverage.data_base_path")
        return False
    elif not exists(str(path_coverage_data["data_base_path"])):
        logging("WARNING", f"path_coverage.data_base_path does not exist: {path_coverage_data['data_base_path']}, creating it")
    config.path_coverage.data_base_path = str(path_coverage_data["data_base_path"])
    
    if "focus_files" in path_coverage_data:
        config.path_coverage.focus_files = []
        for ffile in path_coverage_data["focus_files"]:
            config.path_coverage.focus_files.append(str(ffile))
    else:
        logging("WARNING", "Missing optional field: path_coverage.focus_files, no focus files will be used")
        config.path_coverage.focus_files = []
    
    if "timed_out" in path_coverage_data:
        config.path_coverage.timed_out = int(path_coverage_data["timed_out"])
    else:
        config.path_coverage.timed_out = config.timed_out * 25
        log_info("path_coverage.timed_out not set, using 25 times the test timeout")
    
    if "run_args" in path_coverage_data:
        config.path_coverage.run_args = str(path_coverage_data["run_args"])
    else:
        config.path_coverage.run_args = ''
        log_info("path_coverage.run_args not set, using default None (no additional args)")
    
    return True

def _set_llm_fields(llm_data: Any | None, config: Config) -> bool:
    
    if llm_data is None:
        logging("WARNING", "Missing optional field: llm")
        return False
    
    required_fields = ["api_key", "base_url", "model"]
    for field in required_fields:
        if field not in llm_data:
            logging("WARNING", f"Missing required field: llm.{field}")
            return False
    
    config.llm = Config.LLMConfig()
    config.llm.api_key = str(llm_data["api_key"])
    config.llm.base_url = str(llm_data["base_url"])
    config.llm.model = str(llm_data["model"])
    
    if "timed_out" in llm_data:
        config.llm.timed_out = int(llm_data["timed_out"])
    else:
        config.llm.timed_out = 300
        log_info("llm.timed_out not set, using default 300")
    
    if "generate_after_mutation_num" in llm_data:
        gen_num = int(llm_data["generate_after_mutation_num"])
    else:
        gen_num = 500
        log_info("llm.generate_after_mutation_num not set, using default 500")
    config.llm.generate_after_mutation_num = gen_num
    
    return True

def load_yaml_config(path: str) -> Optional[Config]:
    """
    Load a YAML file from the given path and return its contents as a dictionary.
    If the file does not exist or the format is invalid, return an empty dictionary.
    Handles optional fields: timed_out (default 300), project_home (default ../)...
    All other fields are required. Also checks key paths for existence.
    """
    log_info(f"Loading config file: {str(Path(path).absolute())}")
        
    ret: Config = Config()
    
    with open(path, "r", encoding="utf-8") as f:
        data = yaml.safe_load(f)
        if not isinstance(data, dict):
            logging("ERROR", f"Config file format error: not a dict")
            return None
        
        if _set_gb_fields(data, ret) is False:
            return None
        
        if _set_allfuzzer_fields(data.get("allfuzzer", None), ret) is False:
            return None
        
        if _set_seed_gen_fields(data.get("seed_generator", None), ret) is False:
            return None
        
        seed_mut_sel_data = set_seed_mut_sel_fields(data.get("seed_mutator_select", None))        
        if not seed_mut_sel_data:
            return None
        ret.mut_seed_sel_config = seed_mut_sel_data
        
        llm_mark: bool = False            
        
        if _set_code_coverage_fields(data.get("code_coverage", None), ret) is False:
            ret.code_coverage = None
        else:
            llm_mark = True
            
        if _set_path_coverage_fields(data.get("path_coverage", None), ret) is False:
            ret.path_coverage = None
        else:
            llm_mark = True
        
        if not llm_mark or _set_llm_fields(data.get("llm", None), ret) is False:
            ret.llm = None
        
        ret.config_file = path

    return ret

def reload_config(config: Config) -> Optional[Config]:
    """
    Reload the configuration from the original config file.
    """
    if not hasattr(config, "config_file"):
        logging("ERROR", "Original config file path not found in config object")
        return None
    return load_yaml_config(config.config_file)
