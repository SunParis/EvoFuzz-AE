
from typing import Any

from utils.fs import *
from utils.log import *
    
class MutatorDir:
    structure_mutator_dir: str
    statement_mutator_dir: str
    essential_mutator_dir: str

class MutatorSelect:
    structure_mut_select_min: int
    structure_mut_select_max: int
    statement_mut_select_min: int
    statement_mut_select_max: int

class PriorityAdjustment:
    initial_priority: float
    unsafe_initial_priority: float
    new_bugs: float
    new_code_paths: float
    new_code_diff: float
    new_code_lines: float
    nothing: float
    run_failed: float
    timed_out: float

class SeedMutSelConfig:
    statistics_dir: str
    seed_dir: str
    mut_dir: MutatorDir
    mut_select_num: MutatorSelect
    seed_priority_adjust: PriorityAdjustment
    mut_priority_adjust: PriorityAdjustment


def set_seed_mut_sel_fields(seed_mut_sel_data: Any | None) -> SeedMutSelConfig | None:
    if seed_mut_sel_data is None:
        logging("ERROR", "Missing required field: seed_mutator_select")
        return None
    
    ret = SeedMutSelConfig()
    
    if 'statistics_dir' not in seed_mut_sel_data:
        logging("ERROR", "Missing required field: seed_mutator_select.statistics_dir")
        return None
    ret.statistics_dir = str(seed_mut_sel_data['statistics_dir'])
    
    if 'seed_dir' not in seed_mut_sel_data:
        logging("ERROR", "Missing required field: seed_mutator_select.seed_dir")
        return None
    elif not exists(str(seed_mut_sel_data['seed_dir'])):
        ensure_dir(str(seed_mut_sel_data['seed_dir']))
        log_info(f"seed_mutator_select.seed_dir does not exist, creating: {seed_mut_sel_data['seed_dir']}")
    ret.seed_dir = str(seed_mut_sel_data['seed_dir'])
    
    # MutatorDir
    if 'mutator_dir' not in seed_mut_sel_data:
        logging("ERROR", "Missing required field: seed_mutator_select.mutator_dir")
        return None
    mut_dir_data = seed_mut_sel_data['mutator_dir']
    ret.mut_dir = MutatorDir()
    required_mut_dir_fields = [
        'structure_mutator_dir',
        'statement_mutator_dir',
        'essential_mutator_dir'
    ]
    for field in required_mut_dir_fields:
        if field not in mut_dir_data:
            logging("ERROR", f"Missing required field: seed_mutator_select.mut_dir.{field}")
            return None
        elif not exists(str(mut_dir_data[field])):
            logging("ERROR", f"seed_mutator_select.mut_dir.{field} does not exist: {mut_dir_data[field]}")
            return None
        setattr(ret.mut_dir, field, str(mut_dir_data[field]))
    
    # MutatorSelect
    if 'mut_select_num' not in seed_mut_sel_data:
        logging("ERROR", "Missing required field: seed_mutator_select.mut_select_num")
        return None
    mut_select_data = seed_mut_sel_data['mut_select_num']
    ret.mut_select_num = MutatorSelect()
    required_mut_select_fields = [
        'structure_mutator_select_num',
        'statement_mutator_select_num'
    ]
    for field in required_mut_select_fields:
        if field not in mut_select_data:
            logging("ERROR", f"Missing required field: seed_mutator_select.mut_select_num.{field}")
            return None
        if 'min_num' not in mut_select_data[field]:
            logging("ERROR", f"Missing required field: seed_mutator_select.mut_select_num.{field}.min_num")
            return None
        if 'max_num' not in mut_select_data[field]:
            logging("ERROR", f"Missing required field: seed_mutator_select.mut_select_num.{field}.max_num")
            return None
    ret.mut_select_num.structure_mut_select_min = int(mut_select_data['structure_mutator_select_num']['min_num'])
    ret.mut_select_num.structure_mut_select_max = int(mut_select_data['structure_mutator_select_num']['max_num'])
    ret.mut_select_num.statement_mut_select_min = int(mut_select_data['statement_mutator_select_num']['min_num'])
    ret.mut_select_num.statement_mut_select_max = int(mut_select_data['statement_mutator_select_num']['max_num'])
    if ret.mut_select_num.structure_mut_select_min < 0 or ret.mut_select_num.structure_mut_select_max < 0 or \
       ret.mut_select_num.statement_mut_select_min < 0 or ret.mut_select_num.statement_mut_select_max < 0:
        logging("ERROR", "Mutator select numbers must be non-negative")
        return None
    
    if ret.mut_select_num.structure_mut_select_min > ret.mut_select_num.structure_mut_select_max:
        logging("ERROR", "structure_mutator_select_num.min_num cannot be greater than structure_mutator_select_num.max_num")
        logging("WARNING", "exchanging structure_mutator_select_num.min_num and structure_mutator_select_num.max_num to fix the issue")
        ret.mut_select_num.structure_mut_select_min, ret.mut_select_num.structure_mut_select_max = \
            ret.mut_select_num.structure_mut_select_max, ret.mut_select_num.structure_mut_select_min
    if ret.mut_select_num.statement_mut_select_min > ret.mut_select_num.statement_mut_select_max:
        logging("ERROR", "statement_mutator_select_num.min_num cannot be greater than statement_mutator_select_num.max_num")
        logging("WARNING", "exchanging statement_mutator_select_num.min_num and statement_mutator_select_num.max_num to fix the issue")
        ret.mut_select_num.statement_mut_select_min, ret.mut_select_num.statement_mut_select_max = \
            ret.mut_select_num.statement_mut_select_max, ret.mut_select_num.statement_mut_select_min
    
    # PriorityAdjustment
    def set_priority_adjustment(adj_data: Any, adj_obj: PriorityAdjustment, prefix: str) -> bool:
        required_adj_fields = [
            'initial_priority',
            'new_bugs',
            'new_code_paths',
            'new_code_lines',
            'nothing',
            'run_failed',
            'timed_out'
        ]
        for field in required_adj_fields:
            if field not in adj_data:
                logging("ERROR", f"Missing required field: {prefix}.{field}")
                return False
            setattr(adj_obj, field, float(adj_data[field]))
        if 'unsafe_initial_priority' in adj_data:
            adj_obj.unsafe_initial_priority = float(adj_data['unsafe_initial_priority'])
        else:
            adj_obj.unsafe_initial_priority = adj_obj.initial_priority
        if 'new_code_diff' in adj_data:
            adj_obj.new_code_diff = float(adj_data['new_code_diff'])
        else:
            adj_obj.new_code_diff = adj_obj.new_code_paths
        return True
    if 'seed_priority_adjustment' not in seed_mut_sel_data:
        logging("ERROR", "Missing required field: seed_mutator_select.seed_priority_adjustment")
        return None
    ret.seed_priority_adjust = PriorityAdjustment()
    if not set_priority_adjustment(seed_mut_sel_data['seed_priority_adjustment'], ret.seed_priority_adjust, "seed_mutator_select.seed_priority_adjust"):
        return None
    if 'mutator_priority_adjustment' not in seed_mut_sel_data:
        logging("ERROR", "Missing required field: seed_mutator_select.mutator_priority_adjustment")
        return None
    ret.mut_priority_adjust = PriorityAdjustment()
    if not set_priority_adjustment(seed_mut_sel_data['mutator_priority_adjustment'], ret.mut_priority_adjust, "seed_mutator_select.mut_priority_adjust"):
        return None
    
    return ret

def to_float(val: Any, default: float = 0.0) -> float:
    try:
        return float(val)
    except (ValueError, TypeError):
        return default
