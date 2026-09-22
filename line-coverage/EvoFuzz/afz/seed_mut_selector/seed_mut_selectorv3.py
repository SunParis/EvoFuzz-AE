import math, random
from typing import Dict, Set, Tuple

from evaluation.score import Score
from seed_mut_selector.api import Selector
from seed_mut_selector.config import *
from utils.mp_type import *
from utils.tree import *
from utils.csv2 import *
from utils.fs import *
from utils.log import *


SHOW_DETAILS = os.getenv("SHOW_SELECTOR_DETAILS", "False").lower() == "true"

# Hyperparameters
ALPHA = to_float(os.getenv("ALPHA", "0.75"), 0.75) # The bigger ALPHA is, the faster the score will decay, and the more recent scores will be emphasized
TRANS_DECAY = to_float(os.getenv("TRANS_DECAY", "0.5"), 0.5) # The bigger TRANS_DECAY is, the faster the score will decay
C_EXPLORE = to_float(os.getenv("C_EXPLORE", "2.0"), 2.0) # The bigger C_EXPLORE is, the more exploration will be emphasized during selection.
FAILED_PENALTY = to_float(os.getenv("FAILED_PENALTY", "0.9"), 0.9) # The penalty for a failed run, which will be subtracted from the reward.
TIMEDOUT_PENALTY = to_float(os.getenv("TIMEDOUT_PENALTY", "0.5"), 0.5) # The penalty for a timed out run, which will be subtracted from the reward.

# Labels for CSV data
SEED_PATH_LABEL: str = 'seed_path'
MUT_PATH_LABEL: str = 'mutator_path'
MUT_TYPE_LABEL: str = 'mutator_type'
SCORE_LABEL: str = 'score'
HEAD_MUT: str = 'nil'
PPARENT_SEED: str = 'nil'
PPARENT_SEED_LABEL: str = 'parent_seed'


class HistoryScoreData:
    
    def __init__(self, init_data: str | None = None):
        import ast
        self.history_data: List[Tuple[float, float]] = []
        self.data: float = math.inf
        if init_data is not None and len(init_data.strip()) > 0:
            try:
                self.data = float(init_data.split("#")[0])
                init_data = init_data.split("#")[1]
            except Exception as e:
                logging("ERROR", f"Error parsing count score from HistoryScoreData string: {init_data}. Error: {e}")
                self.data = math.inf
            try:
                parsed_data = ast.literal_eval(init_data.strip())
                if isinstance(parsed_data, list) and all(isinstance(x, tuple) for x in parsed_data) \
                    and all(len(x) == 2 for x in parsed_data):
                    self.history_data = [(float(score), float(alpha)) for score, alpha in parsed_data]
                else:
                    logging("ERROR", f"Invalid format for HistoryScoreData: {init_data}. Expected a list of numbers.")
            except Exception as e:
                logging("ERROR", f"Error parsing HistoryScoreData from string: {init_data}. Error: {e}")
                self.history_data = []
    
    def update_data(self, new_data: float, alpha: float = ALPHA):
        if self.is_none():
            self.history_data.append((new_data, 1.0))
        else:
            if alpha < 0 or alpha > 1:
                logging("WARNING", f"Invalid alpha value: {alpha}. Alpha should be in the range [0, 1]. Using default value {ALPHA}.")
                alpha = ALPHA
            self.history_data.append((new_data, alpha))
        self.data = self.get_score()
    
    def is_none(self) -> bool:
        return len(self.history_data) == 0
    
    def get_score(self) -> float:
        ret: float = 0.0
        if len(self.history_data) == 0:
            ret = math.inf
        else:
            for score, alpha in self.history_data:
                ret = score * alpha + ret * (1 - alpha)
        self.data = ret
        return ret
    
    def get_history_len(self) -> int:
        return len(self.history_data)
    
    def __str__(self) -> str:
        ret = f"{self.data:.4f}#" + "[" + ", ".join(f"({score:.4f}, {alpha:.4f})" for score, alpha in self.history_data) + "]"
        return ret

class SeedMutSelectorV3(Selector):
    
    def __init__(self, seed_mut_sel_config: SeedMutSelConfig, org_seed_only: bool = False):
        
        if not exists(seed_mut_sel_config.statistics_dir):
            ensure_dir(seed_mut_sel_config.statistics_dir)
            log_info(f"seed_mutator_select.statistics_dir does not exist, creating: {seed_mut_sel_config.statistics_dir}")
        
        self.seed_prio_adjust: PriorityAdjustment = seed_mut_sel_config.seed_priority_adjust
        self.mut_prio_adjust: PriorityAdjustment = seed_mut_sel_config.mut_priority_adjust
        self.mut_sel_num: MutatorSelect = seed_mut_sel_config.mut_select_num
        self.essential_muts: List[str] = list_all_files_abs(seed_mut_sel_config.mut_dir.essential_mutator_dir, '.java')
        
        self.mut_prio_list_path: str = path_join(
            seed_mut_sel_config.statistics_dir,
            "mutator_priority_list.csv"
        )
        self.mut_mut_data_path: str = path_join(
            seed_mut_sel_config.statistics_dir,
            "mutator_mutator_score.csv"
        )
        self.seed_prio_list_path: str = path_join(
            seed_mut_sel_config.statistics_dir,
            "seed_priority_list.csv"
        )
        self.seed_mut_data_path: str = path_join(
            seed_mut_sel_config.statistics_dir,
            "seed_mutator_score.csv"
        )
        
        self.mut_priority_list: CSV = CSV(self.mut_prio_list_path)
        self.mut_mut_data: CSV = CSV(self.mut_mut_data_path)
        
        self.seed_priority_list: CSV = CSV(self.seed_prio_list_path)
        self.seed_mut_data: CSV = CSV(self.seed_mut_data_path)
        if not _init_data(
            self.mut_priority_list,
            self.mut_mut_data,
            self.seed_priority_list,
            self.seed_mut_data,
            seed_mut_sel_config.mut_dir.structure_mutator_dir,
            seed_mut_sel_config.mut_dir.statement_mutator_dir,
            seed_mut_sel_config.mut_dir.essential_mutator_dir,
            seed_mut_sel_config.seed_dir
        ):
            logging("ERROR", "Failed to initialize data for SeedMutSelector2.")
            raise Exception("Failed to initialize data for SeedMutSelector2.")
        
        self.org_seed_list = list_all_files_abs(seed_mut_sel_config.seed_dir, '.java') if org_seed_only else []
        self.inf_list_check = MpList()
    
    def add_seed(self, seed_path: str, parent_seed: str = '', score: Score | None = None) -> None:
        if not exists(seed_path):
            logging("ERROR", f"Seed path does not exist: {seed_path}")
            return
        for item in self.seed_priority_list.get_data():
            if SEED_PATH_LABEL in item and item[SEED_PATH_LABEL].strip() == seed_path.strip():
                logging("WARNING", f"Seed {seed_path} already exists in seed priority list. Skipping adding this seed.")
                return
        
        parent_seed = parent_seed if len(parent_seed.strip()) > 0 else PPARENT_SEED
        mut_list = _get_mut_list(self.mut_priority_list)
        new_score = HistoryScoreData()
        if score is not None:
            reward_seed, _ = self._score_to_reward(score)
            new_score.update_data(reward_seed)
            log_info(f"Updating score for seed {get_last_path_component(seed_path)} with reward_seed {reward_seed:.4f}")
        new_s_data = {
            SEED_PATH_LABEL: seed_path,
            SCORE_LABEL: str(new_score),
            PPARENT_SEED_LABEL: parent_seed
        }
        self.seed_priority_list.append_row(new_s_data)
        new_sm_data: Dict[str, str] = {
            SEED_PATH_LABEL: seed_path
        }
        for mut in mut_list:
            new_sm_data[mut] = str(HistoryScoreData())
        self.seed_mut_data.append_row(new_sm_data)
        _total_check(self.mut_priority_list, self.mut_mut_data, self.seed_priority_list, self.seed_mut_data)
    
    def add_mutator(self, mut_path: str, is_statement: bool = True, score: Score | None = None) -> None:
        if not exists(mut_path):
            logging("ERROR", f"Mutator path does not exist: {mut_path}")
            return
        mut_type = "statement" if is_statement else "structure"
        mut_list = _get_mut_list(self.mut_priority_list)
        new_score = HistoryScoreData()
        if score is not None:
            _, reward_mut = self._score_to_reward(score)
            new_score.update_data(reward_mut)
        self.mut_priority_list.append_row({
            MUT_PATH_LABEL: mut_path,
            MUT_TYPE_LABEL: mut_type,
            SCORE_LABEL: str(new_score)
        })
        new_mm_data: Dict[str, str] = {
            MUT_PATH_LABEL: mut_path
        }
        for mut in mut_list:
            new_mm_data[mut] = str(HistoryScoreData())
        self.mut_mut_data.append_row(new_mm_data)
        self.mut_mut_data.update_labels([mut_path], str(HistoryScoreData()))
        self.seed_mut_data.update_labels([mut_path], str(HistoryScoreData()))
        _total_check(self.mut_priority_list, self.mut_mut_data, self.seed_priority_list, self.seed_mut_data)
    
    def update_seed_mut_score(self, seed_path: str, mut_path_list: List[str], score: Score) -> None:
        alpha = ALPHA
        reward_seed, reward_mut = self._score_to_reward(score)
        
        inf_list = self.inf_list_check.to_list()
        if seed_path in inf_list:
            inf_list.remove(seed_path)
        for mut in mut_path_list:
            if mut in inf_list:
                inf_list.remove(mut)
        self.inf_list_check.clear()
        self.inf_list_check.extend(inf_list)
        
        log_info(f"Updating score for seed {get_last_path_component(seed_path)} with reward_seed {reward_seed:.4f}")
        for m in mut_path_list:
            log_info(f"Updating score for mutator {get_last_path_component(m)} with reward_mut {reward_mut:.4f}")
        
        mut_path_list_cp: List[str] = []
        for mut in mut_path_list:
            # Essential mutators will not be updated in the mutator-mutator data and mutator priority list,
            # since they are always selected and do not need to be compared with other mutators.
            if mut not in self.essential_muts:
                mut_path_list_cp.append(mut)
        log_debug(f"Updating scores for seed {seed_path} and mutators {mut_path_list_cp} with reward_seed {reward_seed:.4f} and reward_mut {reward_mut:.4f}")
        
        # 1. Seed Data Update with Decay
        curr_alpha = alpha
        seed_data = self.seed_priority_list.get_data()
        curr_seed = seed_path
        while curr_alpha >= 0.0001 and curr_seed != PPARENT_SEED:
            for item in seed_data:
                if SEED_PATH_LABEL in item and item[SEED_PATH_LABEL].strip() == curr_seed.strip():
                    new_s = HistoryScoreData(item[SCORE_LABEL])
                    new_s.update_data(reward_seed, curr_alpha)
                    item[SCORE_LABEL] = str(new_s)
                    if curr_seed != seed_path:
                        log_info(f"Updating score for ancestor seed {get_last_path_component(curr_seed)} with reward_seed {reward_seed:.4f} (alpha: {curr_alpha:.4f})")
                    if PPARENT_SEED_LABEL in item and len(item[PPARENT_SEED_LABEL].strip()) > 0:
                        curr_seed = item[PPARENT_SEED_LABEL].strip()
                    else:
                        curr_seed = PPARENT_SEED
                        logging("WARNING", f"Parent seed label missing for seed {curr_seed}. Treating it as having no parent.")
                    break
            curr_alpha *= TRANS_DECAY
        self.seed_priority_list.clear_and_write_rows(seed_data)
        log_debug(f"Finished updating seed priority list for seed {seed_path}.")
        
        # 2. Seed-Mutator Data
        seed_mut_data = self.seed_mut_data.get_data()
        for item in seed_mut_data:
            if SEED_PATH_LABEL in item and item[SEED_PATH_LABEL].strip() == seed_path.strip():
                for mut_path in mut_path_list_cp:
                    if mut_path in item:
                        new_s = HistoryScoreData(item[mut_path])
                        new_s.update_data(reward_mut, alpha)
                        item[mut_path] = str(new_s)
                break
        self.seed_mut_data.clear_and_write_rows(seed_mut_data)
        log_debug(f"Finished updating seed-mutator data for seed {seed_path} and mutators {mut_path_list_cp}.")
        
        # 3. Mutator-Mutator Data
        mut_mut_data = self.mut_mut_data.get_data()
        prev_mut = HEAD_MUT
        for mut_path in mut_path_list_cp:
            err = False
            for item in mut_mut_data:
                if MUT_PATH_LABEL in item and item[MUT_PATH_LABEL].strip() == prev_mut:
                    if mut_path in item:
                        new_s = HistoryScoreData(item[mut_path])
                        new_s.update_data(reward_mut, alpha)
                        item[mut_path] = str(new_s)
                        prev_mut = mut_path
                    else:
                        logging("ERROR", f"Mutator path {mut_path} not found in mutator-mutator data for mutator {prev_mut}")
                        err = True
                    break
            if err:
                break
        self.mut_mut_data.clear_and_write_rows(mut_mut_data)
        log_debug(f"Finished updating mutator-mutator data for mutators {mut_path_list_cp}.")
        
        # 4. Mutator Priority List Data
        mut_prio_data = self.mut_priority_list.get_data()
        for mut_path in mut_path_list_cp:
            for item in mut_prio_data:
                if MUT_PATH_LABEL in item and item[MUT_PATH_LABEL].strip() == mut_path.strip():
                    new_s = HistoryScoreData(item[SCORE_LABEL])
                    new_s.update_data(reward_mut, alpha)
                    item[SCORE_LABEL] = str(new_s)
                    break
        self.mut_priority_list.clear_and_write_rows(mut_prio_data)
        log_debug(f"Finished updating mutator priority list for mutators {mut_path_list_cp}.")
        
        # 5. Check
        _total_check(self.mut_priority_list, self.mut_mut_data, self.seed_priority_list, self.seed_mut_data)
        return
    
    def select_seed(self) -> str | None:
        _total_check(self.mut_priority_list, self.mut_mut_data, self.seed_priority_list, self.seed_mut_data)
        s_d: List[Dict[str, str]] = self.seed_priority_list.get_data()
        s_m_d: List[Dict[str, str]] = self.seed_mut_data.get_data()
        mut_list: List[str] = _get_mut_list(self.mut_priority_list)
        sd_len = len(s_d)
        top_rec: List[HistoryScoreData] = []
        seed_name: List[str] = []
                        
        # select 50% top seeds based on mutator performance
        if len(s_m_d) > 0:
            for seed_data in s_m_d:
                m_d: List[HistoryScoreData] = []
                for mut in mut_list:
                    m_d.append(HistoryScoreData(seed_data[mut]))
                top_mut_indices = _select_top_k(m_d, 2)
                if len(top_mut_indices) > 0:
                    top_rec.extend([m_d[i] for i in top_mut_indices])
                    seed_name.extend([seed_data[SEED_PATH_LABEL]] * len(top_mut_indices))
            seed_select_indices = _select_top_k(top_rec, int(max(sd_len / 2, 1)))
            top_rec = [top_rec[i] for i in seed_select_indices]
            seed_name = [seed_name[i] for i in seed_select_indices]
        
        # select final seed based on seed score
        curr_list = self.inf_list_check.to_list()
        for x in s_d:
            if x[SEED_PATH_LABEL] not in curr_list and \
               (len(self.org_seed_list) == 0 or x[SEED_PATH_LABEL] in self.org_seed_list):
                seed_name.extend([x[SEED_PATH_LABEL] for x in s_d])
                top_rec.extend([HistoryScoreData(x[SCORE_LABEL]) for x in s_d])
        ret_list: List[int] = _select_top_k(top_rec)
        if len(ret_list) == 0:
            return None
        
        if str(top_rec[ret_list[0]]) == "inf" and seed_name[ret_list[0]] not in curr_list:
            self.inf_list_check.append(seed_name[ret_list[0]])
        return seed_name[ret_list[0]]
            
    def select_mutator(self, seed_path: str) -> List[str]:
        _total_check(self.mut_priority_list, self.mut_mut_data, self.seed_priority_list, self.seed_mut_data)
        ret: List[str] = []
        struct_muts: List[str] = []
        stmt_muts: List[str] = []
        mut_priority_data: List[Dict[str, str]] = self.mut_priority_list.get_data()
        for mut_data in mut_priority_data:
            if mut_data[MUT_TYPE_LABEL] == 'structure':
                struct_muts.append(mut_data[MUT_PATH_LABEL])
            else:
                stmt_muts.append(mut_data[MUT_PATH_LABEL])
        
        prev = HEAD_MUT
        struct_choose_num: int = random.randint(
            self.mut_sel_num.statement_mut_select_min,
            self.mut_sel_num.statement_mut_select_max
        )
        for _ in range(struct_choose_num):
            prev = self._select_mutator_with_ctx(seed_path, prev, struct_muts)
            ret.append(prev)
            stmt_choose_num: int = random.randint(
                self.mut_sel_num.structure_mut_select_min,
                self.mut_sel_num.structure_mut_select_max
            )
            for __ in range(stmt_choose_num):
                prev = self._select_mutator_with_ctx(seed_path, prev, stmt_muts)
                ret.append(prev)
        
        for ess_mut in self.essential_muts:
            ret.append(ess_mut)
        
        return ret

    def _select_mutator_with_ctx(self, seed_path: str, ctx: str, mut_list: List[str]) -> str:
        priority_data: List[Dict[str, str]] = self.mut_priority_list.get_data()
        mut_mut_data: List[Dict[str, str]] = self.mut_mut_data.get_data()
        seed_mut_data: Dict[str, str] = {}
        for seed_data in self.seed_mut_data.get_data():
            if seed_data[SEED_PATH_LABEL] == seed_path:
                seed_mut_data = seed_data
                break
        
        colect_data: List[List[HistoryScoreData]] = []
        curr_list = self.inf_list_check.to_list()
        
        for mut in mut_list:
            if mut not in curr_list:
                sc1, sc2, sc3 = None, None, None
                
                for mut_data in mut_mut_data:
                    if mut_data[MUT_PATH_LABEL] == ctx:
                        sc1 = HistoryScoreData(mut_data[mut])
                        break
                
                if seed_mut_data.get(mut) is not None:
                    sc2 = HistoryScoreData(seed_mut_data[mut])
                
                for mut_data in priority_data:
                    if mut_data[MUT_PATH_LABEL] == mut:
                        sc3 = HistoryScoreData(mut_data[SCORE_LABEL])
                        break
                    
                if sc1 and sc2 and sc3:
                    colect_data.append([sc1, sc2, sc3])
        
        res = _select_top_k_with_ratio(colect_data, [8, 4, 2])
        if len(mut_list) == 0:
            logging("WARNING", "Mutator list is empty; cannot select mutator.")
            return ''
        if len(res) == 0:
            logging("WARNING", "No mutator selected based on scores; selecting randomly.")
            return random.choice(mut_list)
        
        if str(colect_data[res[0]][0]) == "inf" and mut_list[res[0]] not in curr_list:
            self.inf_list_check.append(mut_list[res[0]])
        
        return mut_list[res[0]]
    
    def _score_to_reward(self, score: Score) -> Tuple[float, float]:
        """
        Map the Score object to scalar rewards for seed and mutator priority updates.
        Return reward_seed, reward_mut
        """        
        
        # if score.run_failed:
        #     return self.mut_prio_adjust.run_failed, self.seed_prio_adjust.run_failed
        
        # if score.timed_out:
        #     return self.mut_prio_adjust.timed_out, self.seed_prio_adjust.timed_out
        
        # convert Score to scalar reward
        reward_seed: float = 0.0
        reward_mut: float = 0.0
        def xy_map(x, x0=500, y_break=5, y_max=7, tail_scale=500):
            """
            x=0 -> 0
            0<x<=x0 -> [1, y_break] with linear mapping
            x>x0 -> (y_break, y_max] with logarithmic compression
            Parameters:
                - x: the input score (e.g., new line num, new path num)
                - x0: the threshold for linear mapping and logarithmic compression (default 800)
                - y_break: the reward value corresponding to x0 (default 8)
                - y_max: the maximum reward value for large x (default 10)
                - tail_scale: the scale for logarithmic compression (default 2000, larger means slower compression)
            """
            if x <= 0:
                return 0.0

            if x <= x0:
                # Let (0, x0] have higher resolution: linear mapping to [1, y_break]
                return 1 + (y_break - 1) * (x / x0)

            # Let (x0, +inf) have lower resolution: logarithmic compression to y_max
            # y = y_break + (y_max - y_break) * log(1 + (x-x0)/tail_scale) / (1 + log(1 + (x-x0)/tail_scale))
            t = math.log1p((x - x0) / tail_scale)  # >=0
            return y_break + (y_max - y_break) * (t / (1 + t))
                
        if score.new_line_num[0] > 0:
            nnn_line_num: float = xy_map(score.new_line_num[0])
            reward_mut += nnn_line_num * self.mut_prio_adjust.new_code_lines
            reward_seed += nnn_line_num * self.seed_prio_adjust.new_code_lines
        
        if score.new_path_num[0] > 0 and score.new_path_num[1] > 0:
            nnn_path_num: float = (1.0 + (score.new_path_num[0] / score.new_path_num[1])) ** 1.5
            reward_mut_new = nnn_path_num * self.mut_prio_adjust.new_code_paths
            reward_seed_new = nnn_path_num * self.seed_prio_adjust.new_code_paths
            reward_mut += reward_mut_new
            reward_seed += reward_seed_new
            log_debug(f"New path num: {nnn_path_num}")
            log_debug(f"Reward from new path num: {reward_mut_new:.4f} for mutator, {reward_seed_new:.4f} for seed")
        
        prev_set = _route_data_log_to_set(score.data.prev_score_data_path)
        curr_set = _route_data_log_to_set(score.data.score_data_path)
        if len(prev_set) > 0 and len(curr_set) > 0:
            common = len(prev_set & curr_set)
            reward_mut_new = (1 - (2 * common) / (len(prev_set) + len(curr_set))) * self.mut_prio_adjust.new_code_diff
            reward_seed_new = (1 - (2 * common) / (len(prev_set) + len(curr_set))) * self.seed_prio_adjust.new_code_diff
            reward_mut += reward_mut_new
            reward_seed += reward_seed_new
            log_debug(f"New Code Diff: {self.seed_prio_adjust.new_code_diff}")
            log_debug(f"Reward from path diff: {reward_mut:.4f} for mutator, {reward_seed:.4f} for seed")
        
        if score.find_bug:
            reward_mut += self.mut_prio_adjust.new_bugs
            reward_seed += self.seed_prio_adjust.new_bugs
        
        if score.run_failed:
            reward_mut = reward_mut * FAILED_PENALTY
            reward_seed = reward_seed * FAILED_PENALTY
        elif score.timed_out:
            reward_mut = reward_mut * TIMEDOUT_PENALTY
            reward_seed = reward_seed * TIMEDOUT_PENALTY
        else:
            reward_mut += self.mut_prio_adjust.nothing
            reward_seed += self.seed_prio_adjust.nothing
        
        reward_seed = max(reward_seed, 1)
        reward_mut = max(reward_mut, 1)
        
        return reward_seed, reward_mut

def _del_repeat(lst: List[str]) -> List[str]:
    seen: Set[str] = set()
    ret: List[str] = []
    for item in lst:
        if item not in seen:
            seen.add(item)
            ret.append(item)
    return ret

def _init_data(m_d_: CSV, m_m_d_: CSV, s_d_: CSV, s_m_d_: CSV, struct_mut_dir: str, st_mut_dir: str, ess_mut_dir: str, seed_dir: str) -> bool:
    """
    Initialize data for SeedMutSelector2.
     - `m_d_`: mutator priority list data
     - `m_m_d_`: mutator-mutator score data
     - `s_d_`: seed priority list data
     - `s_m_d_`: seed-mutator score data
     - struct_mut_dir: directory to scan for structure mutators
     - st_mut_dir: directory to scan for statement mutators
     - ess_mut_dir: directory to scan for essential mutators
     - seed_dir: directory to scan for seeds
     - return: whether the initialization is successful
     - The initialization process includes:
        1. Get data from CSVs
        2. Check paths in the data and the directories, and build a list of valid mutator and seed paths
        3. Build new data for the CSVs based on the valid mutator and seed paths, and the existing data in the CSVs
        4. Write back the new data to the CSVs
     - The data in the CSVs is expected to have the following format:
        - `m_d_`: each row has a mutator path and a score (optional)
        - `m_m_d_`: each row has a mutator path and scores for other mutators (optional)
        - `s_d_`: each row has a seed path, a score (optional), and a parent seed path (optional)
        - `s_m_d_`: each row has a seed path and scores for mutators (optional)
     - The score is expected to be a string representation of a list of floats, e.g., "[0.1, 0.2, 0.3]"
     - The function will ensure that all mutator and seed paths in the CSVs exist, and will add any new mutator and seed paths found in the directories to the CSVs with default scores if they are not already in the CSVs.
     - The function will return True if the initialization is successful, and False if there is any error during the process.
     - Strongly recommend to terminate the process if the initialization fails, as it may lead to undefined behavior in the selector.
    """
    try:
        # Get Data
        m_d: List[Dict[str, str]] = m_d_.get_data()
        m_m_d: List[Dict[str, str]] = m_m_d_.get_data()
        s_d: List[Dict[str, str]] = s_d_.get_data()
        s_m_d: List[Dict[str, str]] = s_m_d_.get_data()
        
        # Check Paths
        mut_lst: List[str] = []
        seed_lst: List[str] = []
        for item in m_d:
            if MUT_PATH_LABEL in item and len(item[MUT_PATH_LABEL].strip()) > 0:
                sss = item[MUT_PATH_LABEL].strip()
                if sss not in mut_lst:
                    mut_lst.append(item[MUT_PATH_LABEL].strip())
        
        ess_muts = list_all_files_abs(ess_mut_dir, '.java')
        struct_muts = []
        for mut in list_all_files_abs(struct_mut_dir, '.java'):
            if mut not in ess_muts:
                struct_muts.append(mut)
        
        st_muts = []
        for mut in list_all_files_abs(st_mut_dir, '.java'):
            if mut not in ess_muts and mut not in struct_muts:
                st_muts.append(mut)
        
        for fls in st_muts:
            if fls not in mut_lst:
                mut_lst.append(fls)
        for fls in struct_muts:
            if fls not in mut_lst:
                mut_lst.append(fls)
        for item in s_d:
            if SEED_PATH_LABEL in item and len(item[SEED_PATH_LABEL].strip()) > 0:
                sss = item[SEED_PATH_LABEL].strip()
                if sss not in seed_lst:
                    seed_lst.append(item[SEED_PATH_LABEL].strip())
        for fls in list_all_files_abs(seed_dir, '.java'):
            if fls not in seed_lst:
                seed_lst.append(fls)
        mut_lst = _del_repeat(list_path_exists(mut_lst))
        seed_lst = _del_repeat(list_path_exists(seed_lst))
        
        # Build Data
        # a. m_d
        new_md: List[Dict[str, str]] = []
        mut_lst_cp = mut_lst.copy()
        for item in m_d:
            if MUT_PATH_LABEL in item and len(item[MUT_PATH_LABEL].strip()) > 0:
                mut_path = item[MUT_PATH_LABEL].strip()
                if mut_path not in mut_lst:
                    continue
                new_data: Dict[str, str] = {}
                new_data[MUT_PATH_LABEL] = mut_path
                mut_type_match = True
                mut_type = ''
                if mut_path in st_muts:
                    mut_type = "statement"
                    if MUT_TYPE_LABEL in item and item[MUT_TYPE_LABEL].strip() != "statement":
                        mut_type_match = False
                elif mut_path in struct_muts:
                    mut_type = "structure"
                    if MUT_TYPE_LABEL in item and item[MUT_TYPE_LABEL].strip() != "structure":
                        mut_type_match = False
                else:
                    if MUT_TYPE_LABEL not in item:
                        mut_type_match = False
                    elif item[MUT_TYPE_LABEL].strip() not in ["statement", "structure"]:
                        mut_type_match = False
                    else:
                        mut_type = item[MUT_TYPE_LABEL].strip()
                
                if len(mut_type) <= 0:
                    # The mutator type cannot be determined, we ignore this mutator and do nothing
                    mut_lst = [x for x in mut_lst if x != mut_path]
                    continue
                else:
                    new_data[MUT_TYPE_LABEL] = mut_type
                
                if not mut_type_match:
                    # Which means the mutator type in the data does not match the actual mutator type, 
                    #   we update it to the actual mutator type, and reset the score to default
                    new_data[SCORE_LABEL] = str(HistoryScoreData())
                elif SCORE_LABEL in item and len(item[SCORE_LABEL].strip()) > 0:
                    new_data[SCORE_LABEL] = str(HistoryScoreData(item[SCORE_LABEL].strip()))
                else:
                    new_data[SCORE_LABEL] = str(HistoryScoreData())
                new_md.append(new_data)
                mut_lst_cp = [x for x in mut_lst_cp if x != mut_path]
        for mut_path in mut_lst_cp:
            mut_type = 'statement' if mut_path in st_muts else 'structure' if mut_path in struct_muts else ''
            if len(mut_type) <= 0:
                # The mutator type cannot be determined,
                #   we ignore this mutator and do nothing
                mut_lst = [x for x in mut_lst if x != mut_path]
                continue
            new_md.append({
                MUT_PATH_LABEL: mut_path,
                MUT_TYPE_LABEL: mut_type,
                SCORE_LABEL: str(HistoryScoreData())
            })
        # b. m_m_d
        new_mmd: List[Dict[str, str]] = []
        mut_lst_cp = mut_lst.copy()
        mut_lst_cp.append(HEAD_MUT)
        for item in m_m_d:
            if MUT_PATH_LABEL in item and len(item[MUT_PATH_LABEL].strip()) > 0:
                mut_path = item[MUT_PATH_LABEL].strip()
                if mut_path not in mut_lst and mut_path != HEAD_MUT:
                    continue
                new_data: Dict[str, str] = {}
                new_data[MUT_PATH_LABEL] = mut_path
                for nxt_mut_path in mut_lst:
                    if nxt_mut_path in item:
                        new_data[nxt_mut_path] = str(HistoryScoreData(item[nxt_mut_path].strip()))
                    else:
                        new_data[nxt_mut_path] = str(HistoryScoreData())
                new_mmd.append(new_data)
                mut_lst_cp = [x for x in mut_lst_cp if x != mut_path]
        for mut_path in mut_lst_cp:
            new_data: Dict[str, str] = {}
            new_data[MUT_PATH_LABEL] = mut_path
            for nxt_mut_path in mut_lst:
                new_data[nxt_mut_path] = str(HistoryScoreData())
            new_mmd.append(new_data)
        # c. s_d
        new_sd: List[Dict[str, str]] = []
        seed_lst_cp = seed_lst.copy()
        for item in s_d:
            if SEED_PATH_LABEL in item and len(item[SEED_PATH_LABEL].strip()) > 0:
                seed_path = item[SEED_PATH_LABEL].strip()
                if seed_path not in seed_lst:
                    continue
                if SCORE_LABEL in item and len(item[SCORE_LABEL].strip()) > 0:
                    item[SCORE_LABEL] = str(HistoryScoreData(item[SCORE_LABEL].strip()))
                else:
                    item[SCORE_LABEL] = str(HistoryScoreData())
                if PPARENT_SEED_LABEL in item and len(item[PPARENT_SEED_LABEL].strip()) > 0:
                    parent_seed = item[PPARENT_SEED_LABEL].strip()
                    if parent_seed not in seed_lst:
                        item[PPARENT_SEED_LABEL] = PPARENT_SEED
                    else:
                        item[PPARENT_SEED_LABEL] = parent_seed
                else:
                    item[PPARENT_SEED_LABEL] = PPARENT_SEED
                new_sd.append(item)
                seed_lst_cp = [x for x in seed_lst_cp if x != seed_path]
        for seed_path in seed_lst_cp:
            new_sd.append({
                SEED_PATH_LABEL: seed_path,
                SCORE_LABEL: str(HistoryScoreData())
            })
        # d. s_m_d
        new_smd: List[Dict[str, str]] = []
        seed_lst_cp = seed_lst.copy()
        for item in s_m_d:
            if SEED_PATH_LABEL in item and len(item[SEED_PATH_LABEL].strip()) > 0:
                seed_path = item[SEED_PATH_LABEL].strip()
                if seed_path not in seed_lst:
                    continue
                new_data: Dict[str, str] = {}
                new_data[SEED_PATH_LABEL] = seed_path
                for mut_path in mut_lst:
                    if mut_path in item:
                        new_data[mut_path] = str(HistoryScoreData(item[mut_path].strip()))
                    else:
                        new_data[mut_path] = str(HistoryScoreData())
                new_smd.append(new_data)
                seed_lst_cp = [x for x in seed_lst_cp if x != seed_path]
        for seed_path in seed_lst_cp:
            new_data: Dict[str, str] = {}
            new_data[SEED_PATH_LABEL] = seed_path
            for mut_path in mut_lst:
                new_data[mut_path] = str(HistoryScoreData())
            new_smd.append(new_data)
        
        # Write Back
        ret: bool = True
        ret = ret and m_d_.clear_and_write_rows(new_md)
        ret = ret and m_m_d_.clear_and_write_rows(new_mmd)
        ret = ret and s_d_.clear_and_write_rows(new_sd)
        ret = ret and s_m_d_.clear_and_write_rows(new_smd)
        return ret
    except Exception as e:
        logging("ERROR", f"Failed to initialize data: {e}")
        return False

def _total_check(m_d_: CSV, m_m_d_: CSV, s_d_: CSV, s_m_d_: CSV) -> bool:
    """
    Total check for the data in the CSVs. This function is expected to be called before each selection to ensure the data is up-to-date and valid.
     - `m_d_`: mutator priority list data
     - `m_m_d_`: mutator-mutator score data
     - `s_d_`: seed priority list data
     - `s_m_d_`: seed-mutator score data
     - return: whether the check is successful
     - The check process includes:
        1. Get data from CSVs
        2. Check paths in the data and the directories, and build a list of valid mutator and seed paths
        3. Build new data for the CSVs based on the valid mutator and seed paths, and the existing data in the CSVs
        4. Write back the new data to the CSVs
     - The data in the CSVs is expected to have the following format:
        - `m_d_`: each row has a mutator path and a score (optional)
        - `m_m_d_`: each row has a mutator path and scores for other mutators (optional)
        - `s_d_`: each row has a seed path, a score (optional), and a parent seed path (optional)
        - `s_m_d_`: each row has a seed path and scores for mutators (optional)
     - The score is expected to be a string representation of a list of floats, e.g., "[0.1, 0.2, 0.3]"
     - The function will ensure that all mutator and seed paths in the CSVs exist, and will add any new mutator and seed paths found in the directories to the CSVs with default scores if they are not already in the CSVs.
     - The function will return True if the check is successful, and False if there is any error during the process.
     - Strongly recommend to terminate the process if the check fails, as it may lead to undefined behavior in the selector.
     - Note that this function is expected to be called frequently, so it should be optimized for performance. Consider using caching or other techniques to avoid redundant checks if necessary.
     - Note that this function may also be used to update the data in the CSVs when there are changes in the mutator and seed directories, so it should be designed to handle such updates gracefully.
     - Note that the function may also be used to clean up the data in the CSVs by removing entries that no longer exist in the directories, so it should be designed to handle such clean-up operations as well.
     - Overall, this function is a critical component of the SeedMutSelector2, and its correctness and efficiency are essential for the proper functioning of the selector.
    """
    log_debug("Starting total check for SeedMutSelector2 data.")
    try:
        # Get Data
        log_debug("Getting data from CSVs for total check.")
        m_d: List[Dict[str, str]] = m_d_.get_data()
        m_m_d: List[Dict[str, str]] = m_m_d_.get_data()
        s_d: List[Dict[str, str]] = s_d_.get_data()
        s_m_d: List[Dict[str, str]] = s_m_d_.get_data()
        
        # Check Paths
        log_debug("Checking paths in the data and directories for total check.")
        mut_lst: List[str] = []
        seed_lst: List[str] = []
        for item in m_d:
            if MUT_PATH_LABEL in item and len(item[MUT_PATH_LABEL].strip()) > 0:
                sss = item[MUT_PATH_LABEL].strip()
                if sss not in mut_lst:
                    mut_lst.append(item[MUT_PATH_LABEL].strip())
        for item in s_d:
            if SEED_PATH_LABEL in item and len(item[SEED_PATH_LABEL].strip()) > 0:
                sss = item[SEED_PATH_LABEL].strip()
                if sss not in seed_lst:
                    seed_lst.append(item[SEED_PATH_LABEL].strip())
        mut_lst = _del_repeat(list_path_exists(mut_lst))
        seed_lst = _del_repeat(list_path_exists(seed_lst))
        
        # Build Data
        # a. m_d
        log_debug("Building new data for CSVs based on valid paths and existing data for total check.")
        new_md: List[Dict[str, str]] = []
        mut_lst_cp = mut_lst.copy()
        for item in m_d:
            if MUT_PATH_LABEL in item and len(item[MUT_PATH_LABEL].strip()) > 0:
                mut_path = item[MUT_PATH_LABEL].strip()
                if mut_path not in mut_lst:
                    continue
                new_data: Dict[str, str] = {}
                new_data[MUT_PATH_LABEL] = mut_path
                mut_type = ''
                if MUT_TYPE_LABEL in item and item[MUT_TYPE_LABEL].strip() in ["statement", "structure"]:
                    mut_type = item[MUT_TYPE_LABEL].strip()
                
                if len(mut_type) <= 0:
                    # The mutator type cannot be determined,
                    #   we ignore this mutator and do nothing
                    mut_lst = [x for x in mut_lst if x != mut_path]
                    continue
                else:
                    new_data[MUT_TYPE_LABEL] = mut_type
                
                if SCORE_LABEL in item and len(item[SCORE_LABEL].strip()) > 0:
                    new_data[SCORE_LABEL] = str(HistoryScoreData(item[SCORE_LABEL].strip()))
                else:
                    new_data[SCORE_LABEL] = str(HistoryScoreData())
                new_md.append(new_data)
                mut_lst_cp = [x for x in mut_lst_cp if x != mut_path]
        for mut_path in mut_lst_cp:
            # The mutator info cannot be determined,
            #   we ignore this mutator and do nothing
            mut_lst = [x for x in mut_lst if x != mut_path]
        # b. m_m_d
        log_debug("Building new mutator-mutator data for total check.")
        new_mmd: List[Dict[str, str]] = []
        mut_lst_cp = mut_lst.copy()
        mut_lst_cp.append(HEAD_MUT)
        for item in m_m_d:
            if MUT_PATH_LABEL in item and len(item[MUT_PATH_LABEL].strip()) > 0:
                mut_path = item[MUT_PATH_LABEL].strip()
                if mut_path not in mut_lst and mut_path != HEAD_MUT:
                    continue
                new_data: Dict[str, str] = {}
                new_data[MUT_PATH_LABEL] = mut_path
                for nxt_mut_path in mut_lst:
                    if nxt_mut_path in item:
                        new_data[nxt_mut_path] = str(HistoryScoreData(item[nxt_mut_path].strip()))
                    else:
                        new_data[nxt_mut_path] = str(HistoryScoreData())
                new_mmd.append(new_data)
                mut_lst_cp = [x for x in mut_lst_cp if x != mut_path]
        for mut_path in mut_lst_cp:
            new_data: Dict[str, str] = {}
            new_data[MUT_PATH_LABEL] = mut_path
            for nxt_mut_path in mut_lst:
                new_data[nxt_mut_path] = str(HistoryScoreData())
            new_mmd.append(new_data)
        # c. s_d
        log_debug("Building new seed priority list data for total check.")
        new_sd: List[Dict[str, str]] = []
        seed_lst_cp = seed_lst.copy()
        for item in s_d:
            if SEED_PATH_LABEL in item and len(item[SEED_PATH_LABEL].strip()) > 0:
                seed_path = item[SEED_PATH_LABEL].strip()
                if seed_path not in seed_lst:
                    continue
                if SCORE_LABEL in item and len(item[SCORE_LABEL].strip()) > 0:
                    item[SCORE_LABEL] = str(HistoryScoreData(item[SCORE_LABEL].strip()))
                else:
                    item[SCORE_LABEL] = str(HistoryScoreData())
                if PPARENT_SEED_LABEL in item and len(item[PPARENT_SEED_LABEL].strip()) > 0:
                    parent_seed = item[PPARENT_SEED_LABEL].strip()
                    if parent_seed not in seed_lst:
                        item[PPARENT_SEED_LABEL] = PPARENT_SEED
                    else:
                        item[PPARENT_SEED_LABEL] = parent_seed
                else:
                    item[PPARENT_SEED_LABEL] = PPARENT_SEED
                new_sd.append(item)
                seed_lst_cp = [x for x in seed_lst_cp if x != seed_path]
        for seed_path in seed_lst_cp:
            # Code here will never be executed in normal cases,
            #  howerver we still keep it here for the sake of
            #  completeness and robustness.
            new_sd.append({
                SEED_PATH_LABEL: seed_path,
                SCORE_LABEL: str(HistoryScoreData()),
                PPARENT_SEED_LABEL: PPARENT_SEED
            })
        # d. s_m_d
        log_debug("Building new seed-mutator score data for total check.")
        new_smd: List[Dict[str, str]] = []
        seed_lst_cp = seed_lst.copy()
        for item in s_m_d:
            if SEED_PATH_LABEL in item and len(item[SEED_PATH_LABEL].strip()) > 0:
                seed_path = item[SEED_PATH_LABEL].strip()
                if seed_path not in seed_lst:
                    continue
                new_data: Dict[str, str] = {}
                new_data[SEED_PATH_LABEL] = seed_path
                for mut_path in mut_lst:
                    if mut_path in item:
                        new_data[mut_path] = str(HistoryScoreData(item[mut_path].strip()))
                    else:
                        new_data[mut_path] = str(HistoryScoreData())
                new_smd.append(new_data)
                seed_lst_cp = [x for x in seed_lst_cp if x != seed_path]
        for seed_path in seed_lst_cp:
            new_data: Dict[str, str] = {}
            new_data[SEED_PATH_LABEL] = seed_path
            for mut_path in mut_lst:
                new_data[mut_path] = str(HistoryScoreData())
            new_smd.append(new_data)
        
        # Write Back
        log_debug("Writing back new data to CSVs for total check.")
        ret: bool = True
        ret = ret and m_d_.clear_and_write_rows(new_md)
        ret = ret and m_m_d_.clear_and_write_rows(new_mmd)
        ret = ret and s_d_.clear_and_write_rows(new_sd)
        ret = ret and s_m_d_.clear_and_write_rows(new_smd)
        log_debug("Finished total check for SeedMutSelector2 data.")
        return ret
    except Exception as e:
        logging("ERROR", f"Failed to initialize data: {e}")
        return False

def _get_mut_list(m_d_: CSV) -> List[str]:
    m_d = m_d_.get_data()
    mut_lst: List[str] = []
    for item in m_d:
        if MUT_PATH_LABEL in item and len(item[MUT_PATH_LABEL].strip()) > 0:
            sss = item[MUT_PATH_LABEL].strip()
            if sss not in mut_lst:
                mut_lst.append(item[MUT_PATH_LABEL].strip())
    return mut_lst

def _select_top_k_with_ratio(score_list: List[List[HistoryScoreData]], ratio: List[float], k: int = 1) -> List[int]:
    if len(score_list) == 0 or k <= 0:
        logging("WARNING", "Score list is empty or k is non-positive; cannot select top k.")
        return []
    if len(score_list) < k:
        return list(range(len(score_list)))
    for item in score_list:
        if len(item) != len(ratio):
            logging("ERROR", f"Length of score list and ratio list do not match: {len(item)} vs {len(ratio)}")
            return []
    
    # We use a weighted random selection approach to select the top k items from the score list.
    max_weights = -math.inf
    min_weights = math.inf
    total_tries = 0
    for score_datas in score_list:
        for score_data in score_datas:
            total_tries += score_data.get_history_len()
            score = score_data.get_score()
            if str(score) == "inf":
                continue
            if score > max_weights:
                max_weights = score
            if score < min_weights:
                min_weights = score
    # Avoid negative weights by shifting all scores up by
    # the absolute value of the minimum score plus a small constant
    if str(min_weights) == "inf":
        # all candidates are inf
        min_weights = 1
        max_weights = 1
    elif min_weights <= 0:
        min_weights = abs(min_weights) + 0.0001
    
    weights: List[float] = []
    candidate_list: List[int] = []
    for idx_outside in range(len(score_list)):
        candidate_list.append(idx_outside)
        weights_cnt: List[float] = []
        for idx in range(len(score_list[idx_outside])):
            score = score_list[idx_outside][idx].get_score()
            this_weight = 0.0
            exploration_term = 0.0
            # If the score is infinity, we assign it a weight that is higher than any finite score,
            # so that it will be selected with higher probability than any finite score.
            if str(score) == "inf":
                this_weight = 2.0 * (max_weights + min_weights)
            else:
                this_weight = score + min_weights
            # We also add an exploration term to encourage exploration of items that have been tried less frequently.
            this_tries = score_list[idx_outside][idx].get_history_len()
            if total_tries > 0 and this_tries > 0:
                exploration_term = C_EXPLORE * math.sqrt(math.log(total_tries) / this_tries)
            weights_cnt.append(this_weight + exploration_term)
        for idx in range(len(weights_cnt)):
            weights_cnt[idx] *= ratio[idx]
        weights.append(sum(weights_cnt))
    
    ret: List[int] = []
    for _ in range(min(k, len(candidate_list))):
        pick = random.choices(candidate_list, weights=weights, k=1)[0]
        ret.append(pick)
        # Uncomment the following lines if you want to remove
        # the selected item from the candidate list and weights list.
        # candidate_list.remove(pick)
    log_info(_select_log([], weights, ret))
    return ret

def _select_top_k(score_list: List[HistoryScoreData], k: int = 1) -> List[int]:
    if len(score_list) == 0 or k <= 0:
        return []
    if len(score_list) < k:
        return list(range(len(score_list)))
    
    # We use a weighted random selection approach to select the top k items from the score list.
    max_weights = -math.inf
    min_weights = math.inf
    total_tries = 0
    for score_data in score_list:
        total_tries += score_data.get_history_len()
        score = score_data.get_score()
        if str(score) == "inf":
            continue
        if score > max_weights:
            max_weights = score
        if score < min_weights:
            min_weights = score
    # Avoid negative weights by shifting all scores up by
    # the absolute value of the minimum score plus a small constant
    if str(min_weights) == "inf":
        # all candidates are inf
        min_weights = 1
        max_weights = 1
    elif min_weights < 0:
        min_weights = abs(min_weights) + 0.0001
    
    weights: List[float] = []
    candidate_list: List[int] = []
    for idx in range(len(score_list)):
        score = score_list[idx].get_score()
        candidate_list.append(idx)
        this_weight = 0.0
        exploration_term = 0.0
        # If the score is infinity, we assign it a weight that is higher than any finite score,
        # so that it will be selected with higher probability than any finite score.
        if str(score) == "inf":
            this_weight = 10.0 * (max_weights + min_weights)
        else:
            this_weight = score + min_weights
        # We also add an exploration term to encourage exploration of items that have been tried less frequently.
        this_tries = score_list[idx].get_history_len()
        if total_tries > 0 and this_tries > 0:
            exploration_term = C_EXPLORE * math.sqrt(math.log(total_tries) / this_tries)
        weights.append(this_weight + exploration_term)
    
    ret: List[int] = []
    for _ in range(min(k, len(candidate_list))):
        pick = random.choices(candidate_list, weights=weights, k=1)[0]
        ret.append(pick)
        # Uncomment the following lines if you want to remove
        # the selected item from the candidate list and weights list.
        # candidate_list.remove(pick)
    log_info(_select_log(score_list, weights, ret))
    return ret

def _select_log(org_score_list: List[HistoryScoreData], weight_list: List[float], picked_list: List[int]) -> str:
    if not SHOW_DETAILS:
        return ''
    try:
        if len(picked_list) == 0:
            return "No item selected."
        ret: str = ''
        w_list_cp = []
        for idx in range(len(weight_list)):
            w_list_cp.append((idx, weight_list[idx]))
        w_list_cp.sort(key=lambda x: x[1], reverse=True)
        picked_list_max_idx = -1
        for p_item in picked_list:
            for idx in range(len(w_list_cp)):
                if w_list_cp[idx][0] == p_item:
                    if idx > picked_list_max_idx:
                        picked_list_max_idx = idx
                    break
        ret += f"Selected item indices: {picked_list}, with scores rank: \n"
        for idx in range(min(picked_list_max_idx + 2, len(w_list_cp))):
            if len(org_score_list) > 0:
                exploi_score = org_score_list[w_list_cp[idx][0]].get_score()
                explre_score = weight_list[w_list_cp[idx][0]] - exploi_score
                candidate_info = f"#{w_list_cp[idx][0]}"
                if idx in picked_list:
                    ret += f"  - {candidate_info}, Score: ({exploi_score:.4f}, {explre_score:.4f}) <-- selected\n"
                else:
                    ret += f"  - {candidate_info}, Score: ({exploi_score:.4f}, {explre_score:.4f})\n"
            else:
                candidate_info = f"#{w_list_cp[idx][0]}"
                if idx in picked_list:
                    ret += f"  - {candidate_info}, Weight: {weight_list[w_list_cp[idx][0]]:.4f} <-- selected\n"
                else:
                    ret += f"  - {candidate_info}, Weight: {weight_list[w_list_cp[idx][0]]:.4f}\n"
        return ret
    except Exception as e:
        return f"Failed to generate debug log: {e}"

def _route_data_log_to_set(path: str | None = None) -> Set[str]:
    if path is None or not exists(path):
        return set()
    route_score_path = path_join(get_file_dir(path), get_file_last_name(path, False) + '.score')
    if not exists(route_score_path):
        return set()    
    lines = read_lines(route_score_path)
    if lines is None:
        return set()
    data: Set[str] = set()
    curr_file = ''
    curr_func = ''
    for lines in lines:
        line_str = lines.strip()
        if line_str.startswith('File:::: '):
            curr_file = line_str.split(':::: ')[1]
            if "opto" not in curr_file:
                curr_file = ''
            else:
                curr_file = curr_file[curr_file.rfind('opto'):]
        elif line_str.startswith('Function:::: '):
            if len(curr_file) > 0:
                curr_func = line_str.split(':::: ')[1]
        elif line_str.find(':::: ') != -1:
            continue
        else:
            if len(curr_file) > 0 and len(curr_func) > 0:
                l_str = ''
                prev = -2
                for line_num in line_str.split('->'):
                    try:
                        num = int(line_num.strip())
                        if num > prev:
                            l_str += f"{num},"
                            prev = num
                    except ValueError:
                        continue
                data.add(curr_file + ':::: ' + curr_func + ':::: ' + l_str)
    return data
