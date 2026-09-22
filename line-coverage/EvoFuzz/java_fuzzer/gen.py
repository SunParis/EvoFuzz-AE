from typing import Tuple

def run_cmd(cmd: str, cwd: str | None = None, timeout: int = 60) -> Tuple[int, str, str]:
    import subprocess
    _cmd = f"timeout {timeout}s {cmd}"
    result = subprocess.run(_cmd, shell=True, cwd=cwd, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    return result.returncode, result.stdout.decode(), result.stderr.decode()

def ensure_dir(path: str):
    import os
    os.makedirs(path, exist_ok=True)

def remove_all_class(dir: str) -> None:
    import os
    for file in os.listdir(dir):
        if file.endswith(".class"):
            os.remove(os.path.join(dir, file))

jf_cmd = f"ruby -I ./rb ./rb/Fuzzer.rb -f ./config.yml"

ensure_dir(f"./output")
for idx in range(100):
    f_name = f"Test{idx:04d}"
    print(f"{idx}: Starting Java fuzzer attempt for {f_name}...")
    rc, out, _ = run_cmd(jf_cmd)
    for retry in range(10):
        if rc == 0:
            out = out.replace("Test", f_name)
            with open(f"./output/{f_name}.java", "w", encoding="utf-8") as f:
                f.write(out)
            test_cmd = f"/usr/lib/jvm/java-17-openjdk/bin/javac {f_name}.java"
            rc, _, _ = run_cmd(test_cmd, cwd="./output")
            if rc != 0:
                print(f"{idx}: Compile of {f_name} failed with return code {rc}")
                remove_all_class('./output')
            else:
                test_cmd = f"/usr/lib/jvm/java-17-openjdk/bin/java {f_name}"
                rc, _, _ = run_cmd(test_cmd, cwd="./output")
                remove_all_class('./output')
                if rc != 0:
                    print(f"{idx}: Execution of {f_name} failed with return code {rc}")
                else:
                    print(f"{idx}: Finished processing {f_name}")
                    break
        else:
            print(f"{idx}: Java fuzzer attempt failed. Retrying... ({retry + 1}/5)")
        rc, out, _ = run_cmd(jf_cmd)
    print(f"{idx}: Java fuzzer attempt {'succeeded' if rc == 0 else 'failed after retries'}. Output saved to ./output/{f_name}.java")
