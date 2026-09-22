
import csv

from typing import List, Dict
from utils.time_limit import time_limit
from utils.log import *

class CSV:
    
    def __init__(self, csv_path: str, op_time_limit: int = 20, retry_times: int = 3):
        self.csv_path = csv_path
        self.op_time_limit = op_time_limit
        self.retry_times = retry_times
    
    def _is_empty(self) -> bool:
        """Check if the CSV file is empty."""
        try:
            with open(self.csv_path, mode='r', newline='', encoding='utf-8') as csvfile:
                return csvfile.read(1) == ''
        except FileNotFoundError:
            return True
        except Exception as e:
            logging("ERROR", f"Error checking if CSV is empty: {e}")
            return False
    
    def is_empty(self) -> bool:
        for _ in range(self.retry_times):
            try:
                with time_limit(self.op_time_limit):
                    return self._is_empty()
            except TimeoutError:
                logging("WARNING", f"Timeout while checking if CSV is empty. Retrying... ({_ + 1}/{self.retry_times})")
        raise TimeoutError(f"Failed to check if CSV is empty after {self.retry_times} attempts.")
    
    def _get_labels(self) -> List[str]:
        """Get the list of labels (column headers) from the CSV file."""
        if self.is_empty():
            return []
        with open(self.csv_path, mode='r', newline='', encoding='utf-8') as csvfile:
            reader = csv.DictReader(csvfile)
            return list(reader.fieldnames) if reader.fieldnames else []
        
    def get_labels(self) -> List[str]:
        for _ in range(self.retry_times):
            try:
                with time_limit(self.op_time_limit):
                    return self._get_labels()
            except TimeoutError:
                logging("WARNING", f"Timeout while getting labels from CSV. Retrying... ({_ + 1}/{self.retry_times})")
        raise TimeoutError(f"Failed to get labels from CSV after {self.retry_times} attempts.")
    
    def update_labels(self, new_labels: List[str], default_val: str) -> bool:
        """Update the CSV file to include new labels (columns) with a default value."""
        if len(new_labels) == 0:
            return True
        try:
            fieldnames = []
            rows = []
            if not self.is_empty():
                with open(self.csv_path, mode='r', newline='', encoding='utf-8') as csvfile:
                    reader = csv.DictReader(csvfile)
                    rows = [row for row in reader]
                    fieldnames = list(reader.fieldnames) if reader.fieldnames else []
            
            for label in new_labels:
                if len(label.strip()) == 0:
                    continue
                if label.strip() not in fieldnames:
                    fieldnames.append(label.strip())
                    for row in rows:
                        row[label.strip()] = default_val
            
            with open(self.csv_path, mode='w', newline='', encoding='utf-8') as csvfile:
                writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
                writer.writeheader()
                writer.writerows(rows)
            return True
        except Exception as e:
            logging("ERROR", f"Error updating labels in CSV: {e}")
            return False
    
    def get_data(self) -> List[Dict[str, str]]:
        """Get all data from the CSV file as a list of dictionaries."""
        if self.is_empty():
            return []
        with open(self.csv_path, mode='r', newline='', encoding='utf-8') as csvfile:
            reader = csv.DictReader(csvfile)
            ret = [row for row in reader]
            return ret if ret else []
    
    def append_row(self, row: Dict[str, str]) -> bool:
        """Append a single row to the CSV file."""
        try:
            w_d = row
            if self.is_empty():
                # If the file is empty, write the header first
                with open(self.csv_path, mode='w', newline='', encoding='utf-8') as csvfile:
                    writer = csv.DictWriter(csvfile, fieldnames=row.keys())
                    writer.writeheader()
            else:
                lbs = self.get_labels()
                w_d = {}
                for lb in lbs:
                    if lb in row:
                        w_d[lb] = row[lb]
                    else:
                        raise ValueError(f"Missing label '{lb}' in the row to append.")
            with open(self.csv_path, mode='a', newline='', encoding='utf-8') as csvfile:
                writer = csv.DictWriter(csvfile, fieldnames=w_d.keys())
                writer.writerow(w_d)
            return True
        except Exception as e:
            logging("ERROR", f"Error appending row to CSV({self.csv_path}): {e}")
            return False
    
    def append_rows(self, rows: List[Dict[str, str]]) -> bool:
        """Append multiple rows to the CSV file."""
        if len(rows) == 0:
            return True
        try:
            if self.is_empty():
                # If the file is empty, write the header first
                with open(self.csv_path, mode='w', newline='', encoding='utf-8') as csvfile:
                    writer = csv.DictWriter(csvfile, fieldnames= rows[0].keys())
                    writer.writeheader()
            with open(self.csv_path, mode='a', newline='', encoding='utf-8') as csvfile:
                if not rows:
                    return True
                writer = csv.DictWriter(csvfile, fieldnames=rows[0].keys())
                for row in rows:
                    writer.writerow(row)
            return True
        except Exception as e:
            logging("ERROR", f"Error appending rows to CSV({self.csv_path}): {e}")
            return False
    
    def clear_and_write_rows(self, rows: List[Dict[str, str]]) -> bool:
        """Clear the CSV file and write new rows."""
        try:
            with open(self.csv_path, mode='w', newline='', encoding='utf-8') as csvfile:
                if not rows:
                    return True
                writer = csv.DictWriter(csvfile, fieldnames=rows[0].keys())
                writer.writeheader()
                writer.writerows(rows)
            return True
        except Exception as e:
            logging("ERROR", f"Error clearing and writing rows to CSV({self.csv_path}): {e}")
            return False
    
    def clear(self) -> bool:
        """Clear all data from the CSV file."""
        try:
            with open(self.csv_path, mode='w', newline='', encoding='utf-8') as csvfile:
                csvfile.truncate()
            return True
        except Exception as e:
            logging("ERROR", f"Error clearing CSV: {e}")
            return False

