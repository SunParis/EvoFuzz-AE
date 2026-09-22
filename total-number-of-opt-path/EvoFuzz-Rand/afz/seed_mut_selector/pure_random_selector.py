
from typing import List, Tuple

from evaluation.score import Score
from seed_mut_selector.api import Selector
from seed_mut_selector.config import *

from utils.fs import list_all_files_abs

class PureRandomSelector(Selector):
    
    def __init__(self, seed_mut_sel_config: SeedMutSelConfig):
        self.seed_dir = seed_mut_sel_config.seed_dir
        self.essn_mut_dir = seed_mut_sel_config.mut_dir.essential_mutator_dir
        self.stmt_mut_dir = seed_mut_sel_config.mut_dir.statement_mutator_dir
        self.srct_mut_dir = seed_mut_sel_config.mut_dir.structure_mutator_dir
        self.min_stmt_mut_num = seed_mut_sel_config.mut_select_num.statement_mut_select_min
        self.max_stmt_mut_num = seed_mut_sel_config.mut_select_num.statement_mut_select_max
        self.min_srct_mut_num = seed_mut_sel_config.mut_select_num.structure_mut_select_min
        self.max_srct_mut_num = seed_mut_sel_config.mut_select_num.structure_mut_select_max
    
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
        import random
        return random.choice(seed_list)
    
    def select_mutator(self, seed_path: str) -> List[str]:
        import random
        stmt_choose_num = random.randint(self.min_stmt_mut_num, self.max_stmt_mut_num)
        srct_choose_num = random.randint(self.min_srct_mut_num, self.max_srct_mut_num)
        struct_mut_list, stmt_mut_list, essential_mut_list = self._get_mut_list()
        all_mut_list = struct_mut_list + stmt_mut_list
        selected_mut_list = []
        for _ in range(srct_choose_num + stmt_choose_num):
            if len(all_mut_list) == 0:
                break
            mut = random.choice(all_mut_list)
            selected_mut_list.append(mut)
        selected_mut_list.extend(essential_mut_list)
        return selected_mut_list
    

