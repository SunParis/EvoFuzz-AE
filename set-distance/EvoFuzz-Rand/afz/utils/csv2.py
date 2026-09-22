from typing import List, Dict
from utils.log import *
from utils.fs import *

class CSV:
    
    def __init__(self, csv_path: str, op_time_limit: int = 20, delimiter='|'):
        self.csv_path = csv_path
        self.op_time_limit = op_time_limit
        self.delimiter = delimiter
    
    def is_empty(self) -> bool:
        """Check if the CSV file is empty."""
        if not exists(self.csv_path):
            return True
        r_l = read_lines(self.csv_path, timeout_sec=self.op_time_limit)
        return r_l is not None and len(r_l) == 0
    
    def get_labels(self) -> List[str]:
        """Get the list of labels (column headers) from the CSV file."""
        if self.is_empty():
            return []
        r_l = read_lines(self.csv_path, timeout_sec=self.op_time_limit)
        if r_l is None or len(r_l) == 0:
            return []
        return r_l[0].strip().split(self.delimiter)
    
    def update_labels(self, new_labels: List[str], default_val: str) -> bool:
        """Update the CSV file to include new labels (columns) with a default value."""
        if len(new_labels) == 0:
            return True
        r_l = read_lines(self.csv_path, timeout_sec=self.op_time_limit)
        if r_l is None or len(r_l) == 0:
            return True
        old_labels = r_l[0].strip().split(self.delimiter)
        for lb in new_labels:
            if lb not in old_labels:
                r_l[0] = r_l[0] + self.delimiter + lb
                for i in range(1, len(r_l)):
                    r_l[i] = r_l[i] + self.delimiter + default_val
        return write_text(self.csv_path, '\n'.join(r_l), timeout_sec=self.op_time_limit)
    
    def get_data(self) -> List[Dict[str, str]]:
        """Get all data from the CSV file as a list of dictionaries."""
        if self.is_empty():
            return []
        r_l = read_lines(self.csv_path, timeout_sec=self.op_time_limit)
        if r_l is None or len(r_l) == 0:
            return []
        lbs = r_l[0].strip().split(self.delimiter)
        data = []
        for line in r_l[1:]:
            values = line.strip().split(self.delimiter)
            row_dict = {lbs[i]: values[i] if i < len(values) else '' for i in range(len(lbs))}
            data.append(row_dict)
        return data
    
    def append_row(self, row: Dict[str, str]) -> bool:
        """Append a single row to the CSV file."""
        r_l = read_lines(self.csv_path, timeout_sec=self.op_time_limit)
        new_lbs = list(row.keys())
        if r_l is not None and len(r_l) > 0:
            new_lbs = r_l[0].strip().split(self.delimiter)
        else:
            r_l = []
            r_l.append(self.delimiter.join(new_lbs))
        new_data = []
        for lb in new_lbs:
            new_data.append(row.get(lb, ''))
        r_l.append(self.delimiter.join(new_data))
        return write_text(self.csv_path, '\n'.join(r_l), timeout_sec=self.op_time_limit)
    
    def append_rows(self, rows: List[Dict[str, str]]) -> bool:
        """Append multiple rows to the CSV file."""
        if len(rows) == 0:
            return True
        r_l = read_lines(self.csv_path, timeout_sec=self.op_time_limit)
        new_lbs = list(rows[0].keys())
        if r_l is not None and len(r_l) > 0:
            new_lbs = r_l[0].strip().split(self.delimiter)
        else:
            r_l = []
            r_l.append(self.delimiter.join(new_lbs))
        for row in rows:
            new_data = []
            for lb in new_lbs:
                new_data.append(row.get(lb, ''))
            r_l.append(self.delimiter.join(new_data))
        return write_text(self.csv_path, '\n'.join(r_l), timeout_sec=self.op_time_limit)
    
    def clear_and_write_rows(self, rows: List[Dict[str, str]]) -> bool:
        """Clear the CSV file and write new rows."""
        if not clear_text(self.csv_path):
            return False
        if len(rows) == 0:
            return True
        r_l = []
        new_lbs = list(rows[0].keys())
        r_l.append(self.delimiter.join(new_lbs))
        for row in rows:
            new_data = []
            for lb in new_lbs:
                new_data.append(row.get(lb, ''))
            r_l.append(self.delimiter.join(new_data))
        return write_text(self.csv_path, '\n'.join(r_l), timeout_sec=self.op_time_limit)
    
    def clear(self) -> bool:
        return clear_text(self.csv_path)

