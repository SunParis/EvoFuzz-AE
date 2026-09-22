
from typing import Dict, List, Optional

from utils.log import logging
from utils.fs import *

VERSION = "1.0.0"

class Node:
    
    def __init__(self, value: str):
        self.value: str = value
        self.parent: Optional['Node'] = None
        self.children: List['Node'] = []
    
    def add_child(self, child: 'Node') -> None:
        self.children.append(child)
        child.parent = self
    
    def get_children(self) -> List['Node']:
        return self.children
    
    def get_value(self) -> str:
        return self.value
    
    def remove_child(self, child: 'Node') -> None:
        self.children.remove(child)
        child.parent = None
    
    def is_root(self) -> bool:
        return self.parent is None
    
    def to_dict_without_children(self) -> Dict[str, str]:
        ret = {"value": self.value}
        if self.parent:
            ret["parent"] = self.parent.value
        return ret

class Tree:
    
    def __init__(self):
        self.root_list: List[Node] = []
        self._node_map: Dict[str, Node] = {}
    
    def find(self, value: str) -> Node | None:
        return self._node_map.get(value, None)
    
    def add_node(self, value: str, parent_value: Optional[str] = None) -> Node:
        new_node = Node(value)
        self._node_map[value] = new_node
        if parent_value:
            parent_node = self.find(parent_value)
            if parent_node:
                parent_node.add_child(new_node)
            else:
                self.root_list.append(new_node)
        else:
            self.root_list.append(new_node)
        return new_node
    
    def remove_node(self, value: str) -> None:
        node = self.find(value)
        if node:
            child_list = node.get_children()
            for child in child_list:
                child.parent = None
                self.root_list.append(child)
            node.children.clear()            
            if node.is_root():
                self.root_list.remove(node)
            if node.parent:
                node.parent.remove_child(node)
            self._node_map.pop(value)                
    
    def to_json(self, out_path: str):
        import json
        
        tree_dict: List[Dict[str, str]] = []
        
        for node in self._node_map.values():
            tree_dict.append(node.to_dict_without_children())
        
        out_str = json.dumps(
            {
                "version": VERSION,
                "nodes": tree_dict
            },
            ensure_ascii=False,
            indent=4
        )
        write_text(out_path, out_str)
        
    @staticmethod
    def from_json(input_path: str) -> 'Tree':
        if not exists(input_path):
            return Tree()
        try:
            import json
            with open(input_path, "r", encoding="utf-8") as f:
                s = f.read()
                data = json.loads(s)
                tree = Tree()
                node_map: Dict[str, Node] = {}
                
                for node_data in data["nodes"]:
                    value = node_data["value"]
                    node = Node(str(value))
                    node_map[value] = node
                    tree._node_map[value] = node
                
                for node_data in data["nodes"]:
                    value = node_data["value"]
                    parent_value = node_data.get("parent")
                    if parent_value:
                        parent_node = node_map.get(parent_value)
                        if parent_node:
                            parent_node.add_child(node_map[value])
                        else:
                            tree.root_list.append(node_map[value])
                    else:
                        tree.root_list.append(node_map[value])
                
                return tree
        except Exception as e:
            logging("ERROR", f"Failed to load tree from json: {e}")
            return Tree()
