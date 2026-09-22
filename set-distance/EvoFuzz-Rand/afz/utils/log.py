from datetime import datetime
from multiprocessing import Lock

import sys, os, time

MSG_TYPES = ["DEBUG", "INFO", "WARNING", "ERROR"]
_LOG_LEVEL_ = os.environ.get('ALLFUZZER_LOG_LEVEL', 'INFO')
_TO_FILE_: str = os.environ.get('ALLFUZZER_LOG_FILE', '')

class Message:
    type: str
    data: str    
    def __init__(self, type: str, data: str):
        self.type = type
        self.data = data
        if self.type not in MSG_TYPES:
            self.type = "INFO"

def log_message(msg: Message):
    if len(msg.data) == 0:
        return
    l_lv = _LOG_LEVEL_
    if l_lv not in MSG_TYPES:
        l_lv = "INFO"
    elif MSG_TYPES.index(msg.type) < MSG_TYPES.index(l_lv):
        return
    timestamp = datetime.now().strftime("%Y-%m-%d-%H-%M-%S")
    _print_with_lock(f"[{timestamp}][{msg.type}] {msg.data}")

def logging(type: str, msg: str):
    if type not in MSG_TYPES:
        type = "INFO"
    log_message(Message(type, msg))

def log_info(data: str):
    log_message(Message("INFO", data))

def log_debug(data: str):
    log_message(Message("DEBUG", data))

_PRINT_LOCK_ = Lock()

def _print_with_lock(data: str):
    with _PRINT_LOCK_:
        if _TO_FILE_ and len(_TO_FILE_) > 0:
            with open(_TO_FILE_, "a") as f:
                f.write(data + "\n")
                f.flush()
        else:
            print(data)
            sys.stdout.flush()
            sys.stderr.flush()

def get_curr_ns() -> int:
    return time.clock_gettime_ns(time.CLOCK_MONOTONIC)

