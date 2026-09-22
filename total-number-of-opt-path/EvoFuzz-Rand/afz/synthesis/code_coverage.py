
from collections.abc import Callable
from typing import Dict, List

from synthesis.synthesis import *

from utils.fs import *
from utils.log import *
from utils.custom_template import *
from utils.llm import *

class CodeCovMutGenerator(MutGenerator):
    
    def __init__(self, llm_client: LLMClient, cov_info_path: str, cov_ctx_generator: Callable[[str], List[Dict[str, str]] | None]) -> None:
        self.llm_client: LLMClient = llm_client
        self.cov_ctx_generator: Callable[[str], List[Dict[str, str]] | None] = cov_ctx_generator
        self.cov_info_path = cov_info_path

    def generate(self, output_folder: str) -> list[str]:
        if self.cov_ctx_generator is None:
            return []
        return _generate_codebricks_from_llm(self.llm_client, self.cov_info_path, self.cov_ctx_generator, output_folder)

def _generate_codebricks_from_llm(
    llm_client: LLMClient,
    cov_info_path: str, 
    cov_ctx_generator: Callable[[str], List[Dict[str, str]] | None],
    output_folder: str
) -> list[str]:
    """
    Generate code bricks using LLM based on coverage information.
    """
    try:
        # Generate coverage info file using lcov
        ctxs = cov_ctx_generator(cov_info_path)
        if ctxs is None:
            logging("WARNING", "No contexts extracted from trace file.")
            return []
        # Now generate code bricks from LLM       
        ret = []
        for ctx in ctxs:
            ask_str = _gen_one_sentence_template_.safe_substitute(
                file=ctx['file'],
                function=ctx['function'],
                context=ctx['context']
            )
            str_form_llm = llm_client.call_llm(ask_str)
            log_info(f"LLM response for context: {str_form_llm}")
            if str_form_llm is None:
                logging("WARNING", f"Warning: LLM returned None for context: \n{ask_str}, \nskipping.")
                continue
            ret.extend(generate_codebricks_from_template(str_form_llm, output_folder, llm_client, 'Code Cov', full_ask=ask_str))
        return ret
    except Exception as e:
        logging("ERROR", f"Error during code brick generation from LLM: {e}")
        return []

_gen_one_sentence_template_ = CustomTemplate(
    f"I'm fuzzing the JVM's JIT compiler to improve C++ code coverage. \n"
    f"Function context (including surrounding lines): \n"
    f"File: %%file \n"
    f"Function code (with +/- 3 lines for context): \n"
    f"```cpp \n"
    f"%%function \n"
    f"``` \n"
    f"The following lines within or near the function have zero coverage (with +/- 5 lines for context): \n"
    f"```cpp \n"
    f"%%context \n"
    f"``` \n"
    f"Analyze this JIT compiler code and determine: \n"
    f"    1. What specific Java code pattern or runtime behavior would trigger these uncovered lines \n"
    f"    2. Any required conditions (e.g., loop iterations, method hotness, specific data types or values) \n"
    f"    3. Java imports that might be needed (if apparent from the code) \n"
    f"Based on this JIT compiler code, what Java pattern would trigger these uncovered lines? \n"
    f"Output ONLY ONE SENTENCE starting with: \"How to [specific Java pattern/behavior] in Java programs\" \n"
    f"Example format: \"How to use deeply nested loops with array access in Java programs\" \n"
    f"Focus on the most direct way to trigger this code path, prioritizing common Java constructs over edge cases. \n"
)

