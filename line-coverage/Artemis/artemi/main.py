# Modified for EvoFuzz-AE: experiment integration and optimization-path collection.
import time
from pathlib import Path
from artemi import main_process
from data_process import data_process
from utils import *

CONFIG_TEMPLATE = str(
    f"out_dir: %%out_dir\n"
    f"num_proc: %%proc_num\n"
    f"prog_timeout: 6000\n"
    f"rand_seed: 1\n"
    f"num_mutation: 8\n"
    f"save_timeouts: False\n"
    f"\n"
    f"java:\n"
    f"  home: %%run_java_home\n"
    f"  classpath: [ ]\n"
    f"\n"
    f"jvm:\n"
    f"  type: hotspot\n"
    f"  options: [ ]\n"
    f"  java_home: %%test_java_home\n"
    f"  classpath: [ ]\n"
    f"  min_api: 28\n"
    f"  host_home: <required-to-change>\n"
    f"  android_home: <required-to-change>\n"
    f"  serial_no: <required-to-change>\n"
    f"  build_tools: <required-to-change>\n"
    f"  app_process: false\n"
    f"\n"
    f"generator:\n"
    f"  name: %%seed_gen\n"
    f"  out_dir: %%jaf_out_dir\n"
    f"  conf: %%java_fuzzer_config\n"
    f"  max_stmt_list_size: 10\n"
    f"  max_nested_branch: 5\n"
    f"  max_nested_loop: 3\n"
    f"  max_nested_try_catch: 2\n"
    f"  exist_dir: %%seed_dir\n"
    f"\n"
    f"artemis:\n"
    f"  jar: %%artemis_jar\n"
    f"  code_bricks: %%cbs_dir\n"
    f"  policy: artemis\n"
    f"  min_loop_trip: 10000\n"
    f"  max_loop_trip: 20000\n"
    f"  extra_opts: {"{ " + "}"}\n"
)

def get_ns() -> str:
    return str(int(time.time() * 1e9))

if __name__ == '__main__':
    import argparse
    ap = argparse.ArgumentParser()
    ap.add_argument("--proc-num", default="16", help="")
    ap.add_argument("--run-java-home", default="", help="")
    ap.add_argument("--test-java-home", default="", help="")
    ap.add_argument("--run-org-jf-conf", default="true", help="")
    ap.add_argument("--out-dir", default="", help="")
    ap.add_argument("--seed-dir", default="", help="")
    args = ap.parse_args()
    
    proc_num = -1
    try:
        proc_num = int(args.proc_num)
    except ValueError:
        pass
    if proc_num <= 0:
        print(f"Invalid proc num: {args.proc_num}")
        exit(1)
        
    run_java_home = str(args.run_java_home)
    test_java_home = str(args.test_java_home)
    if not Path.exists(Path(run_java_home) / "bin" / "java") or \
       not Path.exists(Path(test_java_home) / "bin" / "java"):
        print(f"Invalid Java home: {run_java_home} or {test_java_home}")
        exit(1)
    
    main_py_dir = Path(__file__).parent.resolve()
    
    run_org = bool(str(args.run_org_jf_conf).lower() == "true")
    exist_seed_dir = None
    seed_gen = 'Java*Fuzzer'
    if len(str(args.seed_dir)) > 0 and exists(str(args.seed_dir)):
        exist_seed_dir = Path(str(args.seed_dir))
        seed_gen = 'ExistingTests2'
    java_fuzzer_dir = main_py_dir / 'java_fuzzer'
    java_fuzzer_conf = main_py_dir / 'java_fuzzer' / 'config.org.yml' \
        if run_org else main_py_dir / 'java_fuzzer' / 'config.artemis.yml'
    if not Path.exists(java_fuzzer_conf):
        print(f"Java fuzzer config not found: {java_fuzzer_conf}")
        exit(1)
    
    total_out_dir = Path()
    if args.out_dir is None or len(args.out_dir) == 0:
        total_out_dir = main_py_dir / '..' / 'out'
    out_dir = total_out_dir / 'out'
    jf_out_dir = total_out_dir / 'jaf'
    seed_bkp_dir = total_out_dir / 'seed'
    if not Path.exists(total_out_dir):
        Path(total_out_dir).mkdir(parents=True, exist_ok=True)
    if not Path.exists(out_dir):
        Path(out_dir).mkdir(parents=True, exist_ok=True)
    if not Path.exists(jf_out_dir):
        Path(jf_out_dir).mkdir(parents=True, exist_ok=True)
    if not Path.exists(seed_bkp_dir):
        Path(seed_bkp_dir).mkdir(parents=True, exist_ok=True)
    
    cbs_dir = main_py_dir / '..' / 'code-bricks'
    if not Path.exists(cbs_dir):
        print(f"Code bricks dir not found: {cbs_dir}")
        exit(1)
    
    artemis_jar = main_py_dir / '..' / 'artemis.jar'
    if not Path.exists(artemis_jar):
        print(f"Artemis jar not found: {artemis_jar}")
        exit(1)
    
    path_data_dir = os.getenv("TIME_FILE", str(main_py_dir / ".." / "out" / "out-log"))
    path_data_out = str(main_py_dir / ".." / "out" / f"path_rec_mopfuzzer_{get_ns()}.txt")
    
    config_str = str(CONFIG_TEMPLATE) \
        .replace("%%out_dir", str(out_dir)) \
        .replace("%%proc_num", str(proc_num)) \
        .replace("%%run_java_home", run_java_home) \
        .replace("%%test_java_home", test_java_home) \
        .replace("%%java_fuzzer_config", str(java_fuzzer_conf)) \
        .replace("%%jaf_out_dir", str(jf_out_dir)) \
        .replace("%%artemis_jar", str(artemis_jar)) \
        .replace("%%cbs_dir", str(cbs_dir)) \
        .replace("%%seed_gen", seed_gen) \
        .replace("%%seed_dir", str(exist_seed_dir) if exist_seed_dir is not None else "")
    
    write_config_path = total_out_dir / 'config.yaml'
    with open(write_config_path, 'w') as f:
        f.write(config_str)
    print(f"* Config written to: {write_config_path}")
    print(f"* Start fuzzing. Start time: {time.strftime('%Y-%m-%d %H:%M:%S', time.localtime())}")
    try:
        main_process(str(write_config_path), java_fuzzer_dir, java_fuzzer_conf, seed_bkp_dir, exist_seed_dir)
    except Exception as e:
        print(f'* Exception in main process: {e}')
    kill_all_running_procs()
    print('* Process finished!')
    print("* Data processing...")
    total_path = data_process(path_data_dir, path_data_out)
    print(f"* Total unique path num: {total_path}")
