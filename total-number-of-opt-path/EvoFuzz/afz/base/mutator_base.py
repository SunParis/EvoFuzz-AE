from collections.abc import Callable
from typing import Tuple, List

from config.config import *
from evaluation.score import *
from utils.csv import *
from utils.fs import *
from utils.log import *
from seed_mut_selector.api import Selector

class MutatorBase:
    
    mutator_checker: Callable[[str, str], Tuple[bool, Message]] | None = None
    LabelList: List[str] = ['mut_path', 'priority', 'mut_type']
    
    def __init__(
        self, mut_seed_selector: Selector,
        mut_checkser: Callable[[str, str], Tuple[bool, Message]],
        base_lock
    ):
        """
        Initialize the MutatorBase with configuration and a function to get seed mutation history.
        
        :param mut_seed_selector: An instance of Selector to manage seed and mutator selection.
        :type mut_seed_selector: Selector
        :param mut_checkser: A callable that checks the validity of a mutator.
        :type mut_checkser: Callable[[str, str], Tuple[bool, Message]]
        :param base_lock: A lock object to ensure thread-safe operations.
        :type base_lock: multiprocessing.Lock
        :return: None
        """
        self._mutator_base_lock = base_lock   
        self._mut_seed_selector = mut_seed_selector  
        MutatorBase.mutator_checker = mut_checkser
        
    @staticmethod
    def is_valid_mutator(mutator_path: str, test_dir: str) -> bool:
        """
        Validate the given mutator using the provided mutator checker function.
        
        :param mutator_path: Path to the mutator file.
        :type mutator_path: str
        :param test_dir: Directory where the mutator will be tested.
        :type test_dir: str
        :return: True if the mutator is valid, False otherwise.
        :rtype: bool
        """
        if MutatorBase.mutator_checker is None:
            logging("ERROR", "Mutator checker not set")
            return False
        valid, msg = MutatorBase.mutator_checker(mutator_path, test_dir)
        if not valid:
            logging("ERROR", f"Mutator {mutator_path} is invalid: {msg.data}")
            return False
        logging("INFO", f"Mutator {mutator_path} is valid")
        return True
    
    def get_mutator_list(self, seed_selected: str) -> Tuple[str, int]:
        """
        Get a list of mutators for the selected seed.
        
        :param seed_selected: Path to the selected seed file.
        :type seed_selected: str
        :return: A formatted string containing mutator paths and their priorities.
        :rtype: str
        """
        with self._mutator_base_lock:
            sel_list = self._mut_seed_selector.select_mutator(seed_selected)
            ret: str = ""
            for mut in sel_list:
                if len(mut.strip()) == 0:
                    continue
                if mut.strip().endswith('.unsafe.java'):
                    ret += f"{mut.strip()}, 95\n"
                else:
                    ret += f"{mut.strip()}, 50\n"
            return ret, len(sel_list)
    
    def update_score(self, seed_path: str, mutator_path: str, new_score: Score) -> None:
        """
        Update the score of mutators associated with a given seed.
        For the strategy, see more details in Selector class.
        
        :param seed_path: Path to the seed whose mutators' scores are to be updated.
        :type seed_path: str
        :param mutator_path: String containing mutator paths, each followed by a comma and other data.
        :type mutator_path: str
        :param new_score: The new score to assign to the mutators.
        :type new_score: Score
        :return: None
        """
        mut_list: List[str] = []
        if len(mutator_path.strip()) > 0:
            for line in mutator_path.splitlines():
                if line.strip() == '':
                    continue
                mut_list.append(line[:line.rfind(',')])
        from utils.time_limit import TimeoutErrorReached
        import traceback
        with self._mutator_base_lock:
            try:
                with time_limit(200):
                    self._mut_seed_selector.update_seed_mut_score(
                        seed_path, mut_list, new_score
                    )
            except TimeoutErrorReached:
                log_info(f"Updating bases timed out. Below is the traceback: \n{traceback.format_exc()}")
                raise TimeoutErrorReached
    
    def add_mutator(self, mutator_path: str, score: Score | None = None) -> None:
        """
        Add a mutator to the selector.
        For more details, see the Selector class.
        
        :param mutator_path: Path to the mutator file to be added.
        :type mutator_path: str
        :param score: Optional initial score for the mutator.
        :type score: Score | None
        :return: None
        """
        with self._mutator_base_lock:
            self._mut_seed_selector.add_mutator(mutator_path, score=score)

