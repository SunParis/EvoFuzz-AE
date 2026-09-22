from typing import List, Tuple, Set, Dict

from utils.mp_type import *
from utils.log import *
from utils.fs import *

class PathBase:
    """
    Class to manage path database.
    """
    
    def __init__(self, pkl_path: str):
        self.all_paths_db: str = pkl_path
        self.mp_lock = Lock()
        if not exists(self.all_paths_db):
            with open(self.all_paths_db, 'w') as f:
                f.write('')
    
    def get_data(self) -> Set[str]:
        with self.mp_lock:
            try:
                with open(self.all_paths_db, 'r') as f:
                    lines: List[str] = f.readlines()
                    return set(line.strip() for line in lines if len(line.strip()) > 0)
            except FileNotFoundError:
                return set()
    
    def update_data(self, paths: List[str]) -> int:
        with self.mp_lock:
            try:
                lines: List[str] = []
                with open(self.all_paths_db, 'r') as f:
                    lines = f.readlines()
                total = len(lines)
                with open(self.all_paths_db, 'a') as f:
                    for path in paths:
                        if path.strip() not in lines:
                            f.write(path.strip() + '\n')
                            total += 1
                return total
            except FileNotFoundError:
                return -1
    
    def size(self) -> int:
        with self.mp_lock:
            try:
                with open(self.all_paths_db, 'r') as f:
                    lines: List[str] = f.readlines()
                    return len(set(line.strip() for line in lines if len(line.strip()) > 0))
            except FileNotFoundError:
                return 0
    
    def get_all_paths(self) -> Dict[Tuple[str, str], List[List[int]]]:
        curr_data = self.get_data()
        ret: Dict[Tuple[str, str], List[List[int]]] = {}
        for path in curr_data:
            if path.strip() == '':
                continue
            try:
                file_fn, path_data = path.strip().split(':::', 1)
                file, fn = file_fn.split(':::', 1)
                path_list: List[int] = [int(x) for x in path_data.split('->') if x.strip().isdigit()]
                key = (file, fn)
                if key not in ret:
                    ret[key] = []
                ret[key].append(path_list)
            except Exception as e:
                logging('ERROR', f"Failed to parse path: {path}. Error: {e}")
                continue
        return ret
        
    def add_path(self, paths: str) -> Tuple[int, int, int]:
        """
        Add paths to the path database.
        
        :param paths: The paths string to be parsed and added.
        :type paths: str
        :param rcov_java_home: The RCOV Java home directory.
        :type rcov_java_home: str
        :return: The number of new paths added, total paths, and current size of the database.
        :rtype: Tuple[int, int, int]
        """
        try:
            data_cp = self.get_data()
            rec_paths: List[str] = []
            ret: int = 0
            total: int = 0
            curr_file: str = ''
            curr_fn: str = ''
            for line in paths.splitlines():
                line = line.strip()
                if line.startswith('File:::: '):
                    curr_file = line[len('File:::: '):].strip()
                    continue
                elif line.startswith('Function:::: '):
                    curr_fn = line[len('Function:::: '):].strip()
                    continue
                elif line.find('::::') != -1:
                    continue
                elif len(line) == 0:
                    continue
                    
                curr_path = curr_file + ':::' + curr_fn + ':::' + line
                total += 1
                if curr_path not in data_cp:
                    if curr_path not in rec_paths:
                        rec_paths.append(curr_path)
                    ret += 1
            db_total = self.update_data(rec_paths)
            return ret, total, db_total
        except Exception as e:
            logging('ERROR', f"Failed to add paths. Error: {e}")
            return 0, 0, -1


    
    
