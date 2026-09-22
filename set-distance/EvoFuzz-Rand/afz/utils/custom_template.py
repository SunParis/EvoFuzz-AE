from string import Template
from typing import Any, Mapping, Optional

class CustomTemplate(Template):
    
    delimiter = '%%'
    
    def __init__(self, template: str):
        super().__init__(template)
    
    def safe_substitute(self, mapping: Optional[Mapping[str, Any]] = None, /, **kwds: Any) -> str:
        # Override to provide custom behavior if needed
        return super().safe_substitute({} if mapping is None else mapping, **kwds)

if __name__ == "__main__":
    # Example usage
    template_str = "Hello, %%name! Welcome to %%place."
    custom_template = CustomTemplate(template_str)
    result = custom_template.safe_substitute(name="Alice", place="Wonderland")
    print(result)  # Output: Hello, Alice! Welcome to Wonderland.
    result = custom_template.safe_substitute(name=";;;", place=";;;")
    print(result)  # Output: Hello, ;;;! Welcome to ;;;.