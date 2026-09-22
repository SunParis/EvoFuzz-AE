# Modified for EvoFuzz-AE: experiment integration and optimization-path collection.
import os
from typing import List, Set, Tuple

class FileData:    
    def __init__(self, file_name: str) -> None:
        self.lines: List[str] = []
        with open(file_name, 'r', encoding='utf-8') as f:
            self.lines = f.readlines()
        self.st_time: int = -1
        self.end_time: int = -1
        self.data: Set[str] = set()
        curr_file = ''
        curr_func = ''
        for lines in self.lines:
            line_str = lines.strip()
            if line_str.startswith('START_NS:::: '):
                self.st_time = int(line_str.split(':::: ')[1])
            elif line_str.startswith('END_NS:::: '):
                self.end_time = int(line_str.split(':::: ')[1])
            elif line_str.startswith('File:::: '):
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
                    self.data.add(curr_file + ':::: ' + curr_func + ':::: ' + l_str)

def data_process(file_dir: str, out_path: str) -> int:
    if not os.path.exists(file_dir):
        return 0
    def list_file(parent_dir: str) -> List[str]:
        import os
        file_list = []
        for root, dirs, files in os.walk(parent_dir):
            for file in files:
                file_list.append(os.path.join(root, file))
        return file_list
    file_list = list_file(file_dir)
    all_data: Set[str] = set()
    out_data: List[Tuple[Tuple[int, int], Tuple[int, int]]] = []
    file_data_list: List[FileData] = []
    for file_name in file_list:
        file_data = FileData(file_name)
        file_data_list.append(file_data)
    file_data_list = sorted(file_data_list, key=lambda x: x.end_time)
    for file_data in file_data_list:
        count = 0
        total_cnt = 0
        for str_data in file_data.data:
            if str_data not in all_data:
                count += 1
                all_data.add(str_data)
            total_cnt += 1
        dddata: Tuple[Tuple[int, int], Tuple[int, int]] = (file_data.st_time, file_data.end_time), (count, total_cnt)
        out_data.append(dddata)
    ret: int = len(all_data)
    with open(out_path, 'w', encoding='utf-8') as f:
        for time_range, new_count in out_data:
            if time_range[0] > 0 and time_range[1] > 0:
                f.write(f"({time_range[0]}, {time_range[1]}) ({new_count[0]}, {new_count[1]})\n")
    return ret
