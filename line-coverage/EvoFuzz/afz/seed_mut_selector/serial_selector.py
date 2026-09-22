
from typing import List, Tuple

from multiprocessing import Value
from evaluation.score import Score
from seed_mut_selector.api import Selector
from seed_mut_selector.config import *

from utils.fs import list_all_files_abs

class SerialSelector(Selector):
    
    def __init__(self, seed_mut_sel_config: SeedMutSelConfig):
        self.seed_dir = seed_mut_sel_config.seed_dir
        self.essn_mut_dir = seed_mut_sel_config.mut_dir.essential_mutator_dir
        self.stmt_mut_dir = seed_mut_sel_config.mut_dir.statement_mutator_dir
        self.srct_mut_dir = seed_mut_sel_config.mut_dir.structure_mutator_dir
        self.min_stmt_mut_num = seed_mut_sel_config.mut_select_num.statement_mut_select_min
        self.max_stmt_mut_num = seed_mut_sel_config.mut_select_num.statement_mut_select_max
        self.min_srct_mut_num = seed_mut_sel_config.mut_select_num.structure_mut_select_min
        self.max_srct_mut_num = seed_mut_sel_config.mut_select_num.structure_mut_select_max
        self.last_seed_index = Value('i', 0)
        self.last_stmt_mut_index = Value('i', 0)
        self.last_srct_mut_index = Value('i', 0)
    
    def _get_mut_list(self) -> Tuple[List[str], List[str], List[str]]:
        essential_mut_list = list_all_files_abs(self.essn_mut_dir, with_suffix='.java')
        
        struct_mut_list = list_all_files_abs(self.srct_mut_dir, with_suffix='.java')
        for mut in essential_mut_list:
            if mut in struct_mut_list:
                struct_mut_list.remove(mut)
        
        stmt_mut_list = list_all_files_abs(self.stmt_mut_dir, with_suffix='.java')
        for mut in essential_mut_list:
            if mut in stmt_mut_list:
                stmt_mut_list.remove(mut)
        for mut in struct_mut_list:
            if mut in stmt_mut_list:
                stmt_mut_list.remove(mut)
        
        return struct_mut_list, stmt_mut_list, essential_mut_list
    
    def add_seed(self, seed_path: str, parent_seed: str = '', score: Score | None = None) -> None:
        pass
    
    def add_mutator(self, mut_path: str, is_statement: bool = True, score: Score | None = None) -> None:
        pass
    
    def update_seed_mut_score(self, seed_path: str, mut_path_list: List[str], score: Score) -> None:
        pass
    
    def select_seed(self) -> str | None:
        seed_list = list_all_files_abs(self.seed_dir, with_suffix='.java')
        if not seed_list or len(seed_list) == 0:
            return None
        with self.last_seed_index.get_lock():
            seed_index = self.last_seed_index.value
            self.last_seed_index.value = (self.last_seed_index.value + 1) % len(seed_list)
        return seed_list[seed_index]
    
    def select_mutator(self, seed_path: str) -> List[str]:
        import random
        stmt_choose_num = random.randint(self.min_stmt_mut_num, self.max_stmt_mut_num)
        srct_choose_num = random.randint(self.min_srct_mut_num, self.max_srct_mut_num)
        struct_mut_list, stmt_mut_list, essential_mut_list = self._get_mut_list()
        selected_mut_list = []
        with self.last_srct_mut_index.get_lock():
            srct_index = self.last_srct_mut_index.value
            self.last_srct_mut_index.value = (self.last_srct_mut_index.value + srct_choose_num) % len(struct_mut_list) if len(struct_mut_list) > 0 else 0
        with self.last_stmt_mut_index.get_lock():
            stmt_index = self.last_stmt_mut_index.value
            self.last_stmt_mut_index.value = (self.last_stmt_mut_index.value + stmt_choose_num) % len(stmt_mut_list) if len(stmt_mut_list) > 0 else 0
        for _ in range(srct_choose_num):
            if len(struct_mut_list) == 0:
                break
            mut = struct_mut_list[srct_index]
            selected_mut_list.append(mut)
            srct_index = (srct_index + 1) % len(struct_mut_list)
            for __ in range(stmt_choose_num):
                if len(stmt_mut_list) == 0:
                    break
                mut = stmt_mut_list[stmt_index]
                selected_mut_list.append(mut)
                stmt_index = (stmt_index + 1) % len(stmt_mut_list)
        selected_mut_list.extend(essential_mut_list)
        return selected_mut_list
    

