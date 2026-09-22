
from multiprocessing import Value
from multiprocessing import Lock
from multiprocessing.managers import SyncManager
from multiprocessing.managers import ListProxy
from typing import List

class MpType:
    mp_manager: SyncManager | None = None
    __mp_lock = Lock()
    __count = Value('i', 0)
    
    def __init__(self) -> None:
        with MpType.__mp_lock:
            MpType.__count.value = MpType.__count.value + 1
            if MpType.mp_manager is None:
                SyncManager.register("dict", dict)
                SyncManager.register("set", set)
                MpType.mp_manager = SyncManager()
                MpType.mp_manager.start()
    
    def __del__(self):
        with MpType.__mp_lock:
            MpType.__count.value = MpType.__count.value - 1
            if MpType.__count.value == 0 and MpType.mp_manager:
                MpType.mp_manager.shutdown()
                MpType.mp_manager = None
            
class MpList(MpType):
    
    def __init__(self) -> None:
        super().__init__()
        assert MpType.mp_manager is not None
        self.mp_list: ListProxy = MpType.mp_manager.list()
    
    def __str__(self) -> str:
        cp: List = self.to_list()
        return str(cp)
        
    def append(self, item) -> None:
        return self.mp_list.append(item)
        
    def extend(self, items) -> None:
        return self.mp_list.extend(items)
    
    def remove(self, items) -> None:
        return self.mp_list.remove(items)
    
    def clear(self):
        self.mp_list[:] = []
    
    def back(self):
        return self.to_list()[-1]

    def __len__(self) -> int:
        return len(self.mp_list)
    
    def __getitem__(self, index: int):
        return self.mp_list[index]
    
    def __iter__(self):
        return iter(self.mp_list)
    
    def to_list(self, sort: bool = False) -> List:
        ret: List = list(self.mp_list)
        if sort:
            ret.sort()
        return ret

# class MpDict(MpType):
    
#     def __init__(self, pkl_path: str) -> None:
#         super().__init__()
#         assert MpType.mp_manager is not None
#         self.mp_dict_lock = MpType.mp_manager.Lock()
#         self.pkl_path = pkl_path        
    
#     def update(self, key, value) -> None:
#         import pickle
#         with self.mp_dict_lock:
#             try:
#                 with open(self.pkl_path, 'rb') as f:
#                     self.mp_dict = pickle.load(f)
#             except FileNotFoundError:
#                 self.mp_dict = dict()
#             self.mp_dict.update({key: value})
#             with open(self.pkl_path, 'wb') as f:
#                 pickle.dump(self.mp_dict, f, protocol=pickle.HIGHEST_PROTOCOL)
    
#     def get(self, key):
#         import pickle
#         with self.mp_dict_lock:
#             try:
#                 with open(self.pkl_path, 'rb') as f:
#                     self.mp_dict = pickle.load(f)
#             except FileNotFoundError:
#                 self.mp_dict = dict()
#             return self.mp_dict.get(key, None)
    
#     def clear(self) -> None:
#         import pickle
#         with self.mp_dict_lock:
#             self.mp_dict = dict()
#             with open(self.pkl_path, 'wb') as f:
#                 pickle.dump(self.mp_dict, f, protocol=pickle.HIGHEST_PROTOCOL)
    
#     def has_key(self, key) -> bool:
#         import pickle
#         with self.mp_dict_lock:
#             try:
#                 with open(self.pkl_path, 'rb') as f:
#                     self.mp_dict = pickle.load(f)
#             except FileNotFoundError:
#                 self.mp_dict = dict()
#             return key in self.mp_dict
    
#     def keys(self):
#         import pickle
#         with self.mp_dict_lock:
#             try:
#                 with open(self.pkl_path, 'rb') as f:
#                     self.mp_dict = pickle.load(f)
#             except FileNotFoundError:
#                 self.mp_dict = dict()
#             return self.mp_dict.keys()
    
#     def copy(self):
#         import pickle
#         with self.mp_dict_lock:
#             try:
#                 with open(self.pkl_path, 'rb') as f:
#                     self.mp_dict = pickle.load(f)
#             except FileNotFoundError:
#                 self.mp_dict = dict()
#             return self.mp_dict.copy()
