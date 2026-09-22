
from abc import ABC, abstractmethod
from typing import List

from evaluation.score import Score
from utils.log import log_info

class Selector(ABC):
    
    @abstractmethod
    def add_seed(self, seed_path: str, parent_seed: str = '', score: Score | None = None) -> None:
        """
        Add a seed file to the selector. To note that this method does not move the seed file
        to any specific location; it only registers the seed file in the selector's internal
        data structures. Calling this method with an illegal seed file path (e.g., a path that
        does not exist) may lead to undefined behavior.
        
        :param seed_path: Path to the seed file.
        :param parent_seed: Path to the parent seed file, if any.
        :param score: Optional initial score for the seed file.
        :return: None
        """
        pass
    
    @abstractmethod
    def add_mutator(self, mut_path: str, is_statement: bool = True, score: Score | None = None) -> None:
        """
        Add a mutator file to the selector. To note that this method does not move the mutator file
        to any specific location; it only registers the mutator file in the selector's internal
        data structures. Calling this method with an illegal mutator file path (e.g., a path that 
        does not exist) may lead to undefined behavior.
        
        :param mut_path: Path to the mutator file.
        :param is_statement: Boolean indicating if the mutator is a statement-level mutator.
        :param score: Optional initial score for the mutator file.
        :return: None
        """
        pass
    
    @abstractmethod
    def update_seed_mut_score(self, seed_path: str, mut_path_list: List[str], score: Score) -> None:
        """
        Update the score associated with a specific seed-mutator pair.
        For some strategies, some mutators may not be associated with certain seeds.
        Consider such case, we merge the `update_seed_score` and `update_mutator_score` methods into one.
        
        :param seed_path: Path to the seed file.
        :param mut_path_list: List of mutator file paths associated with the seed.
        :param score: New score to be assigned to the seed-mutator pair.
        :return: None
        """
        pass
    
    @abstractmethod
    def select_seed(self) -> str | None:
        """
        Select a seed file based on the implemented selection strategy.
        
        :return: Path to the selected seed file, or None if no seed is available.
        """
        pass
    
    @abstractmethod
    def select_mutator(self, seed_path: str) -> List[str]:
        """
        Select mutator files for a given seed file based on the implemented selection strategy.
        
        :param seed_path: Path to the seed file for which mutators are to be selected.
        :return: List of paths to the selected mutator files.
        """
        pass

from seed_mut_selector.config import SeedMutSelConfig as __config__
def create_selector(strategy: str, seed_mut_sel_config: __config__, org_seed_only: bool = False) -> Selector:
    """
    Factory method to create a Selector instance based on the specified strategy.
    
    :param strategy: The selection strategy to be used.
    :return: An instance of a Selector subclass corresponding to the strategy.
    """
    from seed_mut_selector.random_selector import RandomSelector
    from seed_mut_selector.pure_random_selector import PureRandomSelector
    from seed_mut_selector.seed_mut_selector_coarse import SeedMutSelectorCoarse
    from seed_mut_selector.seed_mut_selectorv3 import SeedMutSelectorV3
    from seed_mut_selector.seed_mut_selectorv2 import SeedMutSelectorV2
    from seed_mut_selector.serial_selector import SerialSelector

    if strategy == 'random':
        return RandomSelector(seed_mut_sel_config)
    elif strategy == 'serial':
        return SerialSelector(seed_mut_sel_config)
    elif strategy == 'score_based':
        return SeedMutSelectorV3(seed_mut_sel_config, org_seed_only)
    elif strategy == 'score_based_v2':
        return SeedMutSelectorV2(seed_mut_sel_config)
    elif strategy == 'score_based_v3':
        return SeedMutSelectorV3(seed_mut_sel_config, org_seed_only)
    elif strategy == 'score_based_coarse':
        return SeedMutSelectorCoarse(seed_mut_sel_config, org_seed_only)
    elif strategy == 'pure_random':
        return PureRandomSelector(seed_mut_sel_config)
    else:
        log_info(f"Unknown seed-mutator selection strategy: {strategy}. Defaulting to score_based.")
        return SeedMutSelectorV2(seed_mut_sel_config)  # Default to score_based
