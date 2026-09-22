import os
import time
import random
from typing import List, Tuple
from abc import ABC, abstractmethod

from config.config import *
from evaluation.score import Score
from utils.csv import *
from utils.fs import *
from utils.log import *
from utils.mp_type import *
from utils.custom_template import *
from seed_mut_selector.api import Selector

class seed_generator(ABC):
    """
    Abstract base class for seed generators.
    Seed generators are responsible for generating new seed files.
    """
    @abstractmethod
    def get_seed(self, out_dir: str) -> Tuple[bool, str, Message]:
        """
        Generate a new seed file and place it in the specified output directory.
        
        :param out_dir: The directory where the new seed file should be placed.
        :type out_dir: str
        :return: A tuple containing a boolean indicating success, the path to the new seed file,
                 and a Message object with additional information.
        :rtype: Tuple[bool, str, Message]
        """
        pass
    
    @abstractmethod
    def get_generator_name(self) -> str:
        """
        Get the name of the seed generator.
        
        :return: The name of the seed generator.
        :rtype: str
        """
        pass

class SeedBase:
    
    def __init__(self, seed_dir: str, base_lock,
        possiblity_of_new_seed: float,
        mut_seed_selector: Selector
    ) -> None:
        """
        Initialize the SeedBase.
        
        :param seed_dir: The directory where seed files are stored.
        :type seed_dir: str
        :param base_lock: A lock object to ensure thread-safe operations on the seed base.
        :type base_lock: multiprocessing.Lock
        :param possiblity_of_new_seed: The probability of generating a new seed instead of using an existing one.
        :type possiblity_of_new_seed: float
        :param mut_seed_selector: An instance of Selector to select seeds for mutation.
        :type mut_seed_selector: Selector
        """
        
        self._seed_base_lock = base_lock
        self._seed_dir = seed_dir
        self._possiblity_of_new_seed = possiblity_of_new_seed
        self._mut_seed_selector = mut_seed_selector
        self._seed_generators: List[Tuple[seed_generator, int]] = []
        self._new_seed_dir = path_join(seed_dir, "new_seeds")
        self._acc_same_seed = os.getenv("ACC_SAME_SEED") is not None
        self._acc_same_seed_bkp = path_join(seed_dir, "single_seed_bkp")
        if self._possiblity_of_new_seed <= 0.0:
            # Check any seed under the seed directory can be used
            file_list: List[str] = list_all_files_abs(self._seed_dir, '.java')
            if len(file_list) == 0:
                err_str = f"Possibility of new seed is set to {self._possiblity_of_new_seed}, but no seed found in seed directory {self._seed_dir}."
                logging("ERROR", err_str)
                raise ValueError(err_str)
            
    
    def _generate_new_seed(self) -> Tuple[bool, str]:
        """
        Generate a new seed using a randomly selected seed generator.
        :return: A tuple containing a boolean indicating success, and the path to the new seed file.
        :rtype: Tuple[bool, str]
        """
        generators, priorities = zip(*self._seed_generators)
        random_generator: seed_generator = random.choices(generators, weights=priorities, k=1)[0]
        output_dir: str = mkdir(path_join(self._seed_dir, random_generator.get_generator_name(), '0'))
        success, new_seed_path, msg = random_generator.get_seed(output_dir)
        if not success or not exists(new_seed_path):
            logging("ERROR", f"Failed to generate new seed using {random_generator.get_generator_name()}: {msg.data}")
            return False, ''
        return True, new_seed_path
        
    def add_seed_generator(self, generator: seed_generator | None, priority: int) -> None:
        """
        Add a seed generator to the seed base with the specified priority.
        
        :param generator: An instance of seed_generator to be added.
        :type generator: seed_generator | None
        :param priority: The priority of the seed generator. Higher values indicate higher priority.
        :type priority: int
        """
        if not generator:
            return
        self._seed_generators.append((generator, priority))
    
    def add_seed(self, new_seed_path: str, from_seed: str, score: Score | None = None) -> str | None:
        """
        Add a new seed file to the seed base.
        
        :param new_seed_path: The path to the new seed file to be added.
        :type new_seed_path: str
        :param from_seed: The seed from which the new seed was derived. (May be empty if the seed is generated from scratch)
        :type from_seed: str
        :param score: The score associated with the new seed.
        :type score: Score | None
        :return: The path to the added seed file in the seed base, or None if the addition failed.
        :rtype: str | None
        """
        if len(new_seed_path.strip()) == 0:
            return None
        with self._seed_base_lock:
            return self._add_seed_without_lock(new_seed_path, from_seed, score)
    
    def get_seed(self, seed_output_dir: str) -> Tuple[str | None, str | None, str, str, Message]:
        """
        Retrieve a seed file from the seed base, either by copying an existing seed or generating a new one.
        If no suitable seed is found or based on a probability, a new seed will be generated.
        For the strategy of selecting existing seeds, see more details in the Selector class.
        
        :param seed_output_dir: The directory where the seed file should be placed.
        :type seed_output_dir: str
        :return: A tuple containing the path to the seed in the seed base, the path to the seed file in the output directory,
                 compile arguments, run arguments, and a Message object with additional information.
        :rtype: Tuple[str | None, str | None, str, str, Message]
        """
        with self._seed_base_lock:
            exist_seed = self._get_seed_without_lock()
            if not exist_seed:
                logging("WARNING", "No suitable seed found in seed base.")
                need_new_seed = True
            elif self._acc_same_seed:
                need_new_seed = False
            else:
                need_new_seed = random.random() < self._possiblity_of_new_seed
            
            if need_new_seed:
                sucess, new_seed_path = self._generate_new_seed()
                log_debug(f"Generate new seed: success={sucess}, new_seed_path={new_seed_path}")
                if not sucess:
                    # if generation failed
                    return None, None, '', '', Message("ERROR", "Failed to generate new seed.")
                else:
                    # if generation succeeded
                    sucess = self._add_seed_without_lock(new_seed_path, '', None)
                    if not sucess:
                        return None, None, '', '', Message("ERROR", "Failed to add generated seed to seed base.")
                    ret_path = path_join(seed_output_dir, get_file_last_name(new_seed_path))
                    succ = copy_into(new_seed_path, seed_output_dir, overwrite=True)
                    if not succ:
                        return None, None, '', '', Message("ERROR", "Failed to add generated seed to seed base.")
                    return new_seed_path, ret_path, \
                        SeedBase.get_compile_args_value(new_seed_path), \
                        SeedBase.get_run_args_value(new_seed_path), \
                        Message("INFO", "Generated new seed successfully.")
            else:
                assert exist_seed is not None # The logic ensures exist_seed is not None here
                file_name = get_file_last_name(exist_seed)
                cp_success = copy_into(exist_seed, seed_output_dir, file_name, overwrite=True)
                if not cp_success:
                    return None, None, '', '', Message("ERROR", "Failed to get existing seed from seed base.")
                return exist_seed, path_join(seed_output_dir, file_name), \
                    SeedBase.get_compile_args_value(exist_seed), \
                    SeedBase.get_run_args_value(exist_seed), \
                    Message("INFO", "Copied existing seed from seed base.")
    
    @staticmethod
    def hello_world_java(output_path: str) -> Tuple[str, str, str, Message]:
        """
        Generate a simple "Hello, World!" Java program as a seed file.
        
        :param output_path: The path where the Java seed file should be created.
        :type output_path: str
        :return: A tuple containing the path to the generated seed file, empty compile arguments,
                 empty run arguments, and a Message object with additional information.
        :rtype: Tuple[str, str, str, Message]
        """
        write_text(output_path, _hello_world_java_code.safe_substitute(clz_name=Path(output_path).stem), encoding="utf-8")
        return output_path, '', '', Message("INFO", f"Generated Hello World Java seed at {output_path}.")
    
    def _add_seed_without_lock(self, from_path: str, from_seed: str, score: Score | None) -> str | None:
        """
        Add a new seed file to the seed base without acquiring a lock.
        This method should only be called when the lock is already held.
        
        :param from_path: The path to the new seed file to be added.
        :type from_path: str
        :param from_seed: The seed from which the new seed was derived.
        :type from_seed: str
        :param score: The score associated with the new seed.
        :type score: Score | None
        :return: The path to the added seed file in the seed base, or None if the addition failed.
        :rtype: str | None        
        """
        seed_new_path: str | None = None
        if len(from_seed) == 0 or not self._acc_same_seed:
            if not is_under_dir(from_path, self._seed_dir):
                ensure_dir(self._new_seed_dir)
                seed_new_dir = path_join(mkdir(path_join(self._new_seed_dir, '0')))
                copy_into(from_path, seed_new_dir, overwrite=True)
                seed_new_path = path_join(seed_new_dir, get_file_last_name(from_path))
                if score is not None and exists(score.data.score_data_path):
                    copy_into(score.data.score_data_path, seed_new_dir,
                        get_file_last_name(seed_new_path, False) + ".score", overwrite=True)
                    score.data.score_data_path = path_join(seed_new_dir, get_file_last_name(seed_new_path, False) + ".score")
            else:
                # If the new seed is already under the seed directory, skip cp it
                seed_new_path = from_path
        elif self._acc_same_seed:
            ensure_dir(self._acc_same_seed_bkp)
            # bkp from seed
            copy_into(from_seed, self._acc_same_seed_bkp, get_file_last_name(from_seed) + '.bkp', overwrite=False)
            # overwrite from_seed with new_seed
            copy_into(from_path, self._seed_dir, get_file_last_name(from_seed), overwrite=True)
            seed_new_path = from_seed
            if score is not None and exists(score.data.score_data_path):
                copy_into(score.data.score_data_path, from_path,
                    get_file_last_name(seed_new_path, False) + ".score", overwrite=True)
                score.data.score_data_path = path_join(from_path, get_file_last_name(seed_new_path, False) + ".score")
        if seed_new_path is not None:
            if score is not None:
                score.data.new_seed_path = seed_new_path
            self._mut_seed_selector.add_seed(seed_new_path, from_seed, score=score)
        return seed_new_path

    def _get_seed_without_lock(self) -> str | None:
        """
        Retrieve a seed file from the seed base without acquiring a lock.
        This method should only be called when the lock is already held.
        
        :return: The path to the selected seed file, or None if no suitable seed is found.
        :rtype: str | None
        """
        if self._acc_same_seed:
            # If this flag is set, we always return the same seed (the first one in the sorted list)
            #   to ensure that the same seed is selected each time in single seed mode.
            file_list: List[str] = list_all_files_abs(self._seed_dir, '.java')
            if len(file_list) == 0:
                return None
            file_list.sort() # Ensure the same seed is selected each time in single seed mode
            return file_list[0]
        return self._mut_seed_selector.select_seed()
    
    @staticmethod
    def get_run_args_value(file_path: str) -> str:
        """
        Extract the value of the `// run_args:` annotation from the given Java seed file.
        
        :param file_path: The path to the Java seed file.
        :type file_path: str
        :return: The value of the `// run_args:` annotation if found, otherwise an empty string.
        :rtype: str
        """
        try:
            text = Path(file_path).read_text(encoding="utf-8", errors="ignore")
        except Exception:
            return ''
        
        import re
        pattern = re.search(r'^\s*//\s*run_args:\s*(.*)$', text, flags=re.MULTILINE)
        if not pattern:
            return ''
        return pattern.group(1).strip()
        
    @staticmethod
    def get_compile_args_value(file_path: str) -> str:
        """
        Extract the value of the `// compile_args:` annotation from the given Java seed file.
        
        :param file_path: The path to the Java seed file.
        :type file_path: str
        :return: The value of the `// compile_args:` annotation if found, otherwise an empty string.
        :rtype: str
        """
        try:
            text = Path(file_path).read_text(encoding="utf-8", errors="ignore")
        except Exception:
            return ''
        
        import re
        pattern = re.search(r'^\s*//\s*compile_args:\s*(.*)$', text, flags=re.MULTILINE)
        if not pattern:
            return ''
        return pattern.group(1).strip()
    
# A simple "Hello, World!" Java program template
_hello_world_java_code = CustomTemplate(
    f"public class %%clz_name {{\n" \
    f"    public static void main(String[] args) {{\n" \
    f"        System.out.println(\"Hello, World!\");\n" \
    f"    }}\n" \
    f"}}\n"
)
