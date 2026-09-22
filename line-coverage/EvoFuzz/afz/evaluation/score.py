
from typing import Dict, Tuple

class Score:
    """ Score class to store evaluation results. """
    
    class OtherData:
        def __init__(self) -> None:
            self.new_seed_path: str = ''
            self.org_seed_path: str = ''
            self.mut_select_list: str = ''
            self.prepare_time_ns: int = -1
            self.exec_time_ns: int = -1
            self.score_time_ns: int = -1
            self.update_base_time_ns: int = -1
            self.score_data_path: str = ''
            self.prev_score_data_path: str = ''
    
    def __init__(self, data: OtherData, find_bug: bool = False,
        path_num: Tuple[int, int, int]=(0, 0, -1), line_num: Tuple[int, int]=(0, 0),
        run_failed: bool = False, timed_out: bool = False
    ) -> None:
        self.data = data
        self.find_bug = find_bug
        self.new_path_num = path_num
        self.new_line_num = line_num
        self.run_failed = run_failed
        self.timed_out = timed_out
    
    def __str__(self) -> str:
        return (f"(find_bug={self.find_bug}, new_path_num={self.new_path_num}, "
                f"new_line_num={self.new_line_num}, run_failed={self.run_failed}, "
                f"timed_out={self.timed_out})")
    
    def to_dict(self) -> Dict[str, bool | Tuple[int, int, int] | Tuple[int, int]]:
        return {
            "find_bug": self.find_bug,
            "new_path_num": self.new_path_num,
            "new_line_num": self.new_line_num,
            "run_failed": self.run_failed,
            "timed_out": self.timed_out
        }


