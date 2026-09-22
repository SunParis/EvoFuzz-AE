
from typing import Tuple

from base.seed_base import seed_generator
from utils.log import *
from utils.fs import *

class JTregRunner(seed_generator):
    
    def __init__(self, jtreg_seed_home: str, seed_list_path: str) -> None:
        self.jtreg_seed_home: str = jtreg_seed_home
        self.seed_list_path: str = seed_list_path
    
    def get_seed(self, out_dir: str) -> Tuple[bool, str, Message]:
        with open(self.seed_list_path, "r", encoding="utf-8") as f:
            seeds = f.readlines()
        if not seeds:
            return False, '', Message("ERROR", "No seeds available in the seed list.")
        import random
        selected_seed = random.choice(seeds).strip()
        src_path = path_join(self.jtreg_seed_home, selected_seed)
        dst_path = path_join(out_dir, Path(selected_seed).name)
        ret = copy_into(src_path, out_dir, Path(selected_seed).name, overwrite=True)
        if not ret:
            return False, '', Message("ERROR", f"Failed to copy seed from {src_path} to {dst_path}.")
        return True, dst_path, Message("INFO", f"Copied seed from {src_path} to {dst_path} successfully.")
    
    def get_generator_name(self) -> str:
        return "JTreg"

