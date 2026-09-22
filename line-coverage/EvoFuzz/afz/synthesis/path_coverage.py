import re
import tree_sitter_cpp
from bisect import bisect_right
from dataclasses import dataclass
from collections.abc import Callable
from typing import List, Tuple, Optional
from tree_sitter import Language, Parser, Node
from typing import List, Tuple, Dict, Set, Optional

from utils.llm import *
from utils.log import *
from utils.custom_template import *
from synthesis.synthesis import *

class PathCovMutGenerator(MutGenerator):
    def __init__(self, llm_client: LLMClient, paths_getter: Callable[[], Dict[Tuple[str, str], List[List[int]]]]) -> None:
        self.llm_client: LLMClient = llm_client
        self.paths_getter: Callable[[], Dict[Tuple[str, str], List[List[int]]]] = paths_getter
    
    def generate(self, output_folder: str) -> List[str]:
        return _generate_codebricks_from_llm(self.llm_client, self.paths_getter, output_folder)

def _generate_codebricks_from_llm(
    llm_client: LLMClient,
    paths_getter: Callable[[], Dict[Tuple[str, str], List[List[int]]]],
    output_folder: str
) -> list[str]:
    """
    Generate code bricks using LLM based on path coverage information.
    """
    try:
        # Randomly choose a subset of functions first
        def random_choose_path(
            paths: Dict[Tuple[str, str], List[List[int]]],
            num: int = 50
        ) -> Dict[Tuple[str, str], List[List[int]]]:
            if len(paths.items()) == 0:
                return {}
            import random
            chosen = {}
            items = list(paths.items())
            random.shuffle(items)
            for (func_sig, file_path), path_list in items[:num]:
                chosen[(func_sig, file_path)] = path_list
            return chosen
        # Sort chosen paths by path coverage ratio
        def sort_chosen_paths(paths: Dict[Tuple[str, str], List[List[int]]]):
            def sort_func(item):
                file_path, func_sig = item[0]
                path_list = item[1]
                total_path = get_cfg_path_count(file_path, func_sig)
                return (len(path_list) / total_path) if total_path > 0 else 10
            sorted_items = sorted(
                paths.items(),
                key= sort_func
            )
            return sorted_items
        rd_choose_path = random_choose_path(paths_getter(), num=100)
        if len(rd_choose_path) <= 5:
            logging("WARNING", "No paths extracted from path coverage data.")
            return []
        chosen_paths = sort_chosen_paths(rd_choose_path)[:5]
        
        # Now generate code bricks from LLM
        def list_int_to_str(lst: List[List[int]]) -> str:
            ret_str: str = ''
            for each_path in lst:
                for idx in range(len(each_path)):
                    ret_str += str(each_path[idx])
                    if idx != len(each_path) - 1:
                        ret_str += ' -> '
                ret_str += '\n'
            return ret_str
        ret = []
        for ctx in chosen_paths:
            ask_str = _gen_one_sentence_template_.safe_substitute(
                file=ctx[0][0],
                func=extract_function_with_context(ctx[0][0], ctx[0][1]),
                paths=list_int_to_str(ctx[1])
            )
            str_form_llm = llm_client.call_llm(ask_str)
            log_info(f"LLM response for context: {str_form_llm}")
            if str_form_llm is None:
                logging("WARNING", f"Warning: LLM returned None for context: \n{ask_str}, \nskipping.")
                continue
            ret.extend(generate_codebricks_from_template(str_form_llm, output_folder, llm_client, 'Path Cov', full_ask=ask_str))
        return ret
    except Exception as e:
        logging("ERROR", f"Error during code brick generation from LLM: {e}")
        return []

def _load_cpp_parser() -> Parser:
    """Create and return a Tree-sitter parser for C++."""
    CPP_LANGUAGE = Language(tree_sitter_cpp.language())
    return Parser(CPP_LANGUAGE)

def _read_file_bytes(path: str) -> bytes:
    with open(path, "rb") as f:
        return f.read()


def _normalize_sig(s: str) -> str:
    # Rough normalization: remove all whitespace
    return "".join(c for c in s if not c.isspace())


def _node_text(src: bytes, node: Node) -> str:
    return src[node.start_byte:node.end_byte].decode("utf-8", errors="ignore")


def _find_deep_function_declarator(node: Node) -> Node | None:
    """
    Find the innermost function_declarator inside a declarator.
    In C++, declarators can be nested like pointer_declarator -> function_declarator -> ...
    """
    stack = [node]
    last_func = None
    while stack:
        n = stack.pop()
        if n.type == "function_declarator":
            last_func = n
        for ch in n.children:
            stack.append(ch)
    return last_func


def _find_function_definition_by_signature(tree, src: bytes, sig_no_ret: str) -> Node | None:
    """
    Search the syntax tree for a function_definition whose function_declarator text
    (excluding the return type) matches sig_no_ret after whitespace is removed.

    Example sig_no_ret:
      "NS::Cls::foo(int, const std::string&) const"
    """
    target: str = _normalize_sig(sig_no_ret)

    def traverse(node: Node) -> Node | None:
        if node.type == "function_definition":
            # Typically: declaration_specifiers? + declarator + compound_statement
            decl = None
            for ch in node.children:
                if ch.type.endswith("declarator"):
                    decl = ch
                    break
            if decl is None:
                for ch in node.children:
                    if "declarator" in ch.type:
                        decl = ch
                        break
            if decl is not None:
                fdecl = _find_deep_function_declarator(decl)
                if fdecl is not None:
                    text = _node_text(src, fdecl)
                    if _normalize_sig(text) == target:
                        return node
        for ch in node.children:
            res = traverse(ch)
            if res is not None:
                return res
        return None

    return traverse(tree.root_node)


# ------------- CFG building -------------

_id_counter = 0


def _new_id() -> int:
    global _id_counter
    _id_counter += 1
    return _id_counter


@dataclass
class _CFGNode:
    id: int
    kind: str
    label: Optional[str] = None


class _GraphBuilder:
    def __init__(self, src: bytes):
        self.src = src
        self.nodes: Dict[int, _CFGNode] = {}
        self.edges: Dict[int, Set[int]] = {}
        self.end_id: int = self.add_node("END", "function_end")

    def add_node(self, kind: str, label: Optional[str] = None) -> int:
        nid = _new_id()
        self.nodes[nid] = _CFGNode(nid, kind, label)
        self.edges.setdefault(nid, set())
        return nid

    def add_edge(self, u: int, v: int):
        self.edges.setdefault(u, set()).add(v)

    # Subgraph: entry + exits
    @dataclass
    class Subgraph:
        entry: int
        exits: Set[int]

    @dataclass
    class Context:
        break_target: Optional[int]
        continue_target: Optional[int]

    def build_for_function(self, func_def_node: Node) -> Tuple[int, int]:
        """
        Return the function's entry node id and the unified end node id.
        """
        body: Node | None = None
        for ch in func_def_node.children:
            if ch.type == "compound_statement":
                body = ch
                break
        if body is None:
            raise RuntimeError("Function body (compound_statement) not found.")

        ctx = _GraphBuilder.Context(break_target=None, continue_target=None)
        sub = self.build_compound(body, ctx)
        # Connect all function exits to the END node
        for ex in sub.exits:
            self.add_edge(ex, self.end_id)
        return sub.entry, self.end_id

    def build_compound(self, node: Node, ctx: '_GraphBuilder.Context') -> '_GraphBuilder.Subgraph':
        # compound_statement: "{" ... "}"
        stmts = [ch for ch in node.named_children if ch.type != "comment"]
        return self.build_sequence(stmts, ctx)

    def build_sequence(self, stmt_nodes: List, ctx: '_GraphBuilder.Context') -> '_GraphBuilder.Subgraph':
        if not stmt_nodes:
            nid = self.add_node("NOP", "empty")
            return _GraphBuilder.Subgraph(entry=nid, exits={nid})

        current_sub: Optional[_GraphBuilder.Subgraph] = None
        for sn in stmt_nodes:
            sg = self.build_stmt(sn, ctx)
            if current_sub is None:
                current_sub = sg
            else:
                # Connect previous exits to current entry
                for ex in current_sub.exits:
                    self.add_edge(ex, sg.entry)
                current_sub = _GraphBuilder.Subgraph(entry=current_sub.entry, exits=sg.exits)

        assert current_sub is not None
        return current_sub

    def build_stmt(self, node: Node, ctx: '_GraphBuilder.Context') -> '_GraphBuilder.Subgraph':
        t = node.type

        if t == "compound_statement":
            return self.build_compound(node, ctx)

        if t == "if_statement":
            return self.build_if(node, ctx)

        if t in ("while_statement", "for_statement", "range_based_for_statement", "do_statement"):
            return self.build_loop(node, ctx)

        if t == "switch_statement":
            return self.build_switch(node, ctx)

        if t == "return_statement":
            nid = self.add_node("RETURN", _node_text(self.src, node).strip())
            # Directly go to END; no normal exits
            self.add_edge(nid, self.end_id)
            return _GraphBuilder.Subgraph(entry=nid, exits=set())

        if t == "break_statement":
            nid = self.add_node("BREAK", "break")
            if ctx.break_target is not None:
                self.add_edge(nid, ctx.break_target)
            # No normal exits if no valid break target
            return _GraphBuilder.Subgraph(entry=nid, exits=set())

        if t == "continue_statement":
            nid = self.add_node("CONTINUE", "continue")
            if ctx.continue_target is not None:
                self.add_edge(nid, ctx.continue_target)
            return _GraphBuilder.Subgraph(entry=nid, exits=set())

        # All other statements (declarations, expressions, empty statements, etc.)
        nid = self.add_node("STMT", t)
        return _GraphBuilder.Subgraph(entry=nid, exits={nid})

    def build_if(self, node: Node, ctx: '_GraphBuilder.Context') -> '_GraphBuilder.Subgraph':
        # if_statement: "if" "(" condition ")" consequence ( "else" alternative )?
        conseq = None
        altern = None

        named = [ch for ch in node.named_children]
        # Named children order is typically: condition, consequence, (optional) alternative
        if len(named) >= 2:
            conseq = named[1]
        if len(named) >= 3:
            altern = named[2]

        cond_id = self.add_node("COND", "if_cond")

        if conseq is None:
            then_sg = _GraphBuilder.Subgraph(entry=self.add_node("NOP", "then_empty"),
                                            exits={self.add_node("NOP", "then_end")})
        else:
            then_sg = self.build_stmt(conseq, ctx)

        if altern is not None:
            else_sg = self.build_stmt(altern, ctx)
        else:
            else_sg = _GraphBuilder.Subgraph(entry=self.add_node("NOP", "else_empty"),
                                            exits={self.add_node("NOP", "else_end")})

        # cond -> then.entry / else.entry
        self.add_edge(cond_id, then_sg.entry)
        self.add_edge(cond_id, else_sg.entry)

        # Merge exits
        exits = set()
        exits |= then_sg.exits
        exits |= else_sg.exits
        return _GraphBuilder.Subgraph(entry=cond_id, exits=exits)

    def build_loop(self, node: Node, outer_ctx: '_GraphBuilder.Context') -> '_GraphBuilder.Subgraph':
        t = node.type

        after_loop = self.add_node("JOIN", f"after_{t}")

        if t == "do_statement":
            # do { body } while (cond);
            # Treat as executing body once; break -> after_loop, continue -> after_loop
            body_node = None
            named = [ch for ch in node.named_children]
            if named:
                body_node = named[0]
            if body_node is None:
                entry = self.add_node("NOP", "do_body_empty")
                return _GraphBuilder.Subgraph(entry=entry, exits={after_loop})

            ctx = _GraphBuilder.Context(break_target=after_loop, continue_target=after_loop)
            body_sg = self.build_stmt(body_node, ctx)
            for ex in body_sg.exits:
                self.add_edge(ex, after_loop)
            return _GraphBuilder.Subgraph(entry=body_sg.entry, exits={after_loop})

        # while/for/range-based-for:
        # cond has two branches: false -> after_loop; true -> body (but we only model up to one iteration)
        cond_id = self.add_node("COND", f"{t}_cond")

        body_node = None
        if t == "while_statement":
            named = [ch for ch in node.named_children]
            if len(named) >= 2:
                body_node = named[1]
        elif t == "for_statement":
            named = [ch for ch in node.named_children]
            if named:
                body_node = named[-1]
        elif t == "range_based_for_statement":
            named = [ch for ch in node.named_children]
            if named:
                body_node = named[-1]

        if body_node is None:
            body_entry = self.add_node("NOP", f"{t}_body_empty")
            body_sg = _GraphBuilder.Subgraph(entry=body_entry, exits={body_entry})
        else:
            ctx = _GraphBuilder.Context(break_target=after_loop, continue_target=after_loop)
            body_sg = self.build_stmt(body_node, ctx)

        # cond -> false -> after_loop
        self.add_edge(cond_id, after_loop)
        # cond -> true -> body.entry
        self.add_edge(cond_id, body_sg.entry)
        # body normal exits -> after_loop (only 1 iteration counted)
        for ex in body_sg.exits:
            self.add_edge(ex, after_loop)

        return _GraphBuilder.Subgraph(entry=cond_id, exits={after_loop})

    def build_switch(self, node: Node, outer_ctx: '_GraphBuilder.Context') -> '_GraphBuilder.Subgraph':
        # switch '(' condition ')' statement
        named = [ch for ch in node.named_children]
        if not named:
            sid = self.add_node("COND", "switch_cond")
            after_switch = self.add_node("JOIN", "after_switch")
            self.add_edge(sid, after_switch)
            return _GraphBuilder.Subgraph(entry=sid, exits={after_switch})

        cond_id = self.add_node("COND", "switch_cond")
        body_stmt = named[-1]

        after_switch = self.add_node("JOIN", "after_switch")

        # In switch, break -> after_switch; continue stays for the nearest loop, so pass outer_ctx.continue_target
        ctx = _GraphBuilder.Context(break_target=after_switch, continue_target=outer_ctx.continue_target)

        if body_stmt.type != "compound_statement":
            # Non-braced body: conservative handling as a single statement
            body_sg = self.build_stmt(body_stmt, ctx)
            self.add_edge(cond_id, body_sg.entry)
            for ex in body_sg.exits:
                self.add_edge(ex, after_switch)
            # Also allow a "no-match" edge if no case/default matches
            self.add_edge(cond_id, after_switch)
            return _GraphBuilder.Subgraph(entry=cond_id, exits={after_switch})

        # Braced switch body
        comp = body_stmt
        named_children = [ch for ch in comp.named_children if ch.type != "comment"]

        # Collect labels
        labels = [ch for ch in named_children if ch.type in ("case_statement", "default_statement")]

        if not labels:
            body_sg = self.build_compound(comp, ctx)
            self.add_edge(cond_id, body_sg.entry)
            for ex in body_sg.exits:
                self.add_edge(ex, after_switch)
            self.add_edge(cond_id, after_switch)
            return _GraphBuilder.Subgraph(entry=cond_id, exits={after_switch})

        # Build blocks for each case/default: from label end to next label start (or block end)
        blocks = []
        for i, lbl in enumerate(labels):
            start = lbl.end_byte
            end = labels[i + 1].start_byte if i + 1 < len(labels) else comp.end_byte - 1
            chunk = []
            for ch in named_children:
                if ch.start_byte >= start and ch.end_byte <= end:
                    if ch.type not in ("case_statement", "default_statement"):
                        chunk.append(ch)
            blocks.append((lbl, chunk))

        has_default = any(lbl.type == "default_statement" for lbl, _ in blocks)

        # Build subgraphs per block
        block_entries = []
        block_subgraphs = []
        for (lbl, chunk) in blocks:
            entry = self.add_node("LABEL", _node_text(self.src, lbl).strip())
            if chunk:
                sg = self.build_sequence(chunk, ctx)
                self.add_edge(entry, sg.entry)
                block_entries.append(entry)
                block_subgraphs.append(sg)
            else:
                empty = self.add_node("NOP", "case_empty")
                self.add_edge(entry, empty)
                block_entries.append(entry)
                block_subgraphs.append(_GraphBuilder.Subgraph(entry=empty, exits={empty}))

        # cond -> each label entry
        for ent in block_entries:
            self.add_edge(cond_id, ent)
        # If no default, add "no-match" edge to after_switch
        if not has_default:
            self.add_edge(cond_id, after_switch)

        # Handle fallthrough between blocks
        for i, sg in enumerate(block_subgraphs):
            is_last = (i == len(block_subgraphs) - 1)
            if not is_last:
                next_entry = block_entries[i + 1]
                for ex in list(sg.exits):
                    self.add_edge(ex, next_entry)
            else:
                for ex in sg.exits:
                    self.add_edge(ex, after_switch)

        return _GraphBuilder.Subgraph(entry=cond_id, exits={after_switch})

    # ------------- Path counting on DAG -------------

    def count_paths(self, start_id: int, end_id: int) -> int:
        """
        Count the number of acyclic execution paths from start_id to end_id.
        Loops are modeled as 0-or-1 iteration, so the CFG should be acyclic.
        """
        reachable = self._reachable_from(start_id)
        edges = {u: {v for v in vs if v in reachable} for u, vs in self.edges.items() if u in reachable}

        indeg = {u: 0 for u in reachable}
        for u, vs in edges.items():
            for v in vs:
                indeg[v] = indeg.get(v, 0) + 1

        from collections import deque
        q = deque([u for u in reachable if indeg.get(u, 0) == 0])
        topo = []
        while q:
            u = q.popleft()
            topo.append(u)
            for v in edges.get(u, []):
                indeg[v] -= 1
                if indeg[v] == 0:
                    q.append(v)

        if end_id not in reachable:
            return 0

        ways = {u: 0 for u in reachable}
        ways[start_id] = 1
        for u in topo:
            for v in edges.get(u, []):
                ways[v] = ways.get(v, 0) + ways.get(u, 0)

        return ways.get(end_id, 0)

    def _reachable_from(self, start: int) -> Set[int]:
        seen = set()
        stack = [start]
        while stack:
            u = stack.pop()
            if u in seen:
                continue
            seen.add(u)
            for v in self.edges.get(u, []):
                if v not in seen:
                    stack.append(v)
        return seen

def get_cfg_path_count(file_path: str, func_sig: str) -> int:
    src = _read_file_bytes(file_path)
    
    parser = _load_cpp_parser()
    tree = parser.parse(src)

    func = _find_function_definition_by_signature(tree, src, func_sig)
    if func is None:
        logging("ERROR", f"Function definition not found: {func_sig}")
        return 0

    gb = _GraphBuilder(src)
    start_id, end_id = gb.build_for_function(func)

    return gb.count_paths(start_id, end_id)

def extract_function_with_context(file_path: str,
                                  signature: str,
                                  context_lines: int = 3,
                                  occurrence: int = 1) -> str:
    """
    Extract a C++ function definition (matched by a return-less signature) and
    include N lines of context above and below. Keeps all original comments and formatting.

    Args:
        file_path: str to the C++ source file (UTF-8).
        signature: The function signature WITHOUT return type, e.g. "foo(int a, const std::string& b)".
                   It can span multiple lines in the file; whitespace differences are tolerated.
        context_lines: How many lines above and below the function to include.
        occurrence: 1-based index if multiple matching definitions exist; default is the first.

    Returns:
        A single string. Each line starts with "<line_number>： " (Chinese colon), preserving original comments.

    Raises:
        FileNotFoundError, ValueError if no matching definition found, or unmatched braces, etc.
    """
    with open(file_path, 'r', encoding='utf-8') as f:
        text = f.read()

    # Precompute line starts for fast index->line mapping
    lines = text.splitlines(keepends=True)
    line_starts = []
    pos = 0
    for ln in lines:
        line_starts.append(pos)
        pos += len(ln)
    total_len = len(text)

    def idx_to_line(i: int) -> int:
        # Return 1-based line number
        j = bisect_right(line_starts, i) - 1
        return max(1, j + 1)

    # Build a mask of "code positions" (True = not inside comment/string)
    code_mask = _build_code_mask_cpp(text)

    # Build a regex pattern from the signature, tolerating whitespace/newlines
    sig_pattern = _signature_to_regex(signature)
    sig_re = re.compile(sig_pattern, re.DOTALL)

    # Find all candidate matches of the signature
    candidates = list(sig_re.finditer(text))
    if not candidates:
        raise ValueError("No occurrence of the provided signature found in the file.")

    found_ranges = []
    for m in candidates:
        start = m.start()
        end = m.end()

        # Skip matches that start inside comment/string
        if not code_mask[start]:
            continue

        # Determine if this is a definition: next significant token '{' must appear before ';'
        nxt_kind, brace_idx = _next_code_token_kind(text, end, code_mask, targets=('brace', 'semicolon'))
        if nxt_kind != 'brace' or brace_idx is None:
            # It's likely a declaration/prototype
            continue

        # From the opening '{', find the matching '}' respecting strings/comments
        close_idx = _find_matching_brace(text, brace_idx, code_mask)

        # Expand backward to include return type and qualifiers on the same line
        header_start = _expand_header_start_same_line(text, start, code_mask)

        # Also include immediately preceding template/attribute lines if contiguous
        header_start = _maybe_include_template_or_attribute_lines(text, header_start, code_mask, line_starts)

        found_ranges.append((header_start, close_idx + 1))  # close_idx inclusive, so +1 for slicing

    if not found_ranges:
        raise ValueError("Signature found, but no matching function definition (with a body) was detected.")

    if occurrence < 1 or occurrence > len(found_ranges):
        raise ValueError(f"Requested occurrence {occurrence} out of range (1..{len(found_ranges)}).")

    func_start, func_end = found_ranges[occurrence - 1]

    # Compute context line range
    start_line = idx_to_line(func_start)
    end_line = idx_to_line(max(func_end - 1, 0))

    out_from = max(1, start_line - context_lines)
    out_to = min(len(lines), end_line + context_lines)

    # Format with "<line_number>： " prefix, preserving original text
    out_parts = []
    for ln in range(out_from, out_to + 1):
        raw = lines[ln - 1]
        out_parts.append(f"{ln}： {raw}")

    return "".join(out_parts)


def _signature_to_regex(signature: str) -> str:
    """
    Convert a signature string to a regex that tolerates arbitrary whitespace/newlines.
    Keeps punctuation literal but collapses whitespace to \\s* so it matches across lines.
    """
    sig = signature.strip()
    # Escape everything first
    esc = re.escape(sig)
    # Replace escaped whitespace runs with \s*
    # This handles spaces/tabs/newlines in code.
    pattern = re.sub(r'(?:\\\s)+', r'\\s*', esc)
    # Also allow optional whitespace around commas and parentheses without over-complication:
    # (We already allow \s* globally; no further change needed.)
    return pattern


def _build_code_mask_cpp(text: str) -> List[bool]:
    """
    Build a boolean mask marking positions that are "code" (True) vs inside comments/strings (False).
    Handles:
      - // line comments
      - /* block comments */
      - "string literals" with escapes
      - 'char literals' with escapes
      - Raw strings: R"delim(... )delim"
    """
    n = len(text)
    mask = [True] * n

    i = 0
    while i < n:
        ch = text[i]
        nxt = text[i + 1] if i + 1 < n else ''

        # Line comment //
        if ch == '/' and nxt == '/':
            start = i
            i += 2
            while i < n and text[i] != '\n':
                i += 1
            for k in range(start, i):
                mask[k] = False
            continue

        # Block comment /* ... */
        if ch == '/' and nxt == '*':
            start = i
            i += 2
            while i + 1 < n and not (text[i] == '*' and text[i + 1] == '/'):
                i += 1
            i = min(n, i + 2)  # include closing */
            for k in range(start, i):
                mask[k] = False
            continue

        # Raw string R"delim(... )delim"
        if ch == 'R' and nxt == '"':
            # Parse delimiter until '('
            j = i + 2
            # Delimiter can be empty or up to typically 16 chars; we do generic until '(' or newline
            while j < n and text[j] != '(' and text[j] != '\n':
                j += 1
            if j < n and text[j] == '(':
                delim = text[i + 2:j]
                # Look for )delim"
                terminator = ')' + delim + '"'
                k = text.find(terminator, j + 1)
                if k == -1:
                    # Unterminated raw string; mark until end
                    for t in range(i, n):
                        mask[t] = False
                    break
                else:
                    end_pos = k + len(terminator)
                    for t in range(i, end_pos):
                        mask[t] = False
                    i = end_pos
                    continue
            # If malformed, fall through as normal char
        # Normal string literal "..."
        if ch == '"':
            start = i
            i += 1
            while i < n:
                if text[i] == '\\':
                    i += 2
                    continue
                if text[i] == '"':
                    i += 1
                    break
                i += 1
            for k in range(start, min(i, n)):
                mask[k] = False
            continue

        # Char literal '...'
        if ch == "'":
            start = i
            i += 1
            while i < n:
                if text[i] == '\\':
                    i += 2
                    continue
                if text[i] == "'":
                    i += 1
                    break
                i += 1
            for k in range(start, min(i, n)):
                mask[k] = False
            continue

        i += 1

    return mask


def _next_code_token_kind(text: str, i: int, mask: List[bool], targets=('brace', 'semicolon')) -> Tuple[Optional[str], Optional[int]]:
    """
    From index i forward, skip whitespace and non-code, then return which comes first among
    '{' (brace) and ';' (semicolon). Returns (kind, index), kind in {'brace','semicolon',None}.
    """
    n = len(text)
    j = i
    while j < n:
        if not mask[j]:
            j += 1
            continue
        c = text[j]
        if c.isspace():
            j += 1
            continue
        if c == '{' and 'brace' in targets:
            return 'brace', j
        if c == ';' and 'semicolon' in targets:
            return 'semicolon', j
        # Otherwise keep scanning
        j += 1
    return None, None


def _find_matching_brace(text: str, open_idx: int, mask: List[bool]) -> int:
    """
    Given index of '{' (in code), find matching '}' using depth count, ignoring comments/strings.
    Returns the index of the matching '}'.
    """
    assert text[open_idx] == '{'
    n = len(text)
    depth = 0
    i = open_idx
    while i < n:
        if not mask[i]:
            i += 1
            continue
        c = text[i]
        if c == '{':
            depth += 1
        elif c == '}':
            depth -= 1
            if depth == 0:
                return i
        i += 1
    raise ValueError("Unmatched '{' – could not find closing '}'.")


def _expand_header_start_same_line(text: str, match_start: int, mask: List[bool]) -> int:
    """
    Expand backward on the same line to include return type and qualifiers preceding the function name.
    Stops at the previous newline or file start.
    """
    # Find start of current line
    i = match_start
    while i > 0 and text[i - 1] != '\n':
        i -= 1
    # Now i is the start of line; include from there
    return i


def _maybe_include_template_or_attribute_lines(text: str, header_start: int, mask: List[bool], line_starts: List[int]) -> int:
    """
    If the immediately previous non-empty line starts with 'template' or attribute '[[', include it (and repeat).
    Stops at blank line or non-matching line.
    """
    # Map header_start to line index
    import math
    line_idx = max(0, bisect_right(line_starts, header_start) - 1)

    def line_range(idx: int) -> Tuple[int, int]:
        start = line_starts[idx]
        end = line_starts[idx + 1] if idx + 1 < len(line_starts) else len(text)
        return start, end

    cur_idx = line_idx
    start_pos, _ = line_range(cur_idx)

    # Walk upwards while previous lines are template/attribute and contiguous
    while cur_idx - 1 >= 0:
        p_start, p_end = line_range(cur_idx - 1)
        segment = text[p_start:p_end]
        stripped = segment.strip()
        if stripped == '':
            break
        if stripped.startswith('template') or stripped.startswith('[['):
            start_pos = p_start
            cur_idx -= 1
            continue
        # Also include macro-only attribute-like lines (very heuristic): lines ending with '\' usually are macro continuations
        if segment.rstrip().endswith('\\'):
            start_pos = p_start
            cur_idx -= 1
            continue
        break

    return start_pos

_gen_one_sentence_template_ = CustomTemplate(
    f"You are an expert in JVM HotSpot JIT (C1/C2) internals and I'm now fuzzing the JVM's HotSpot JIT"
    f"to improve C++ code coverage using a mutator-based approach"
    f"which needs a precise instruction for generating Java code that reaches an uncovered basic-block path"
    f"within a specific JIT function. \n"
    f"Function context (exact file or component path): \n"
    f"``` \n"
    f"%%file \n"
    f"``` \n"
    f"Function code (full body with explicit line numbers): \n"
    f"```cpp \n"
    f"%%func \n"
    f"``` \n"
    f"Already covered basic-block paths (as line-number sequences): \n"
    f"``` \n"
    f"%%paths \n"
    f"``` \n"
    f"Analyze this JIT compiler function, construct the control-flow graph, identify one feasible basic-block path"
    f"that is not in the covered set, and determine the minimal self-contained Java source/bytecode pattern and runtime behavior"
    f"that will deterministically execute that path under default HotSpot settings; include specific constructs (loops, branches, exceptions),"
    f"concrete argument values, array/object sizes, any required object state or aliasing, control-flow or exception triggers,"
    f"and an approximate number of warmup iterations needed to reach JIT compilation; prefer the simplest path if several are possible;"
    f"do not rely on any JVM command-line options or compilation flags (assume default tiered compilation and standard Java APIs only)."
    f"Output ONLY ONE plain English sentence starting with \"How to\" that precisely describes the Java pattern/behavior;"
    f"do not include code blocks, quotes, lists, flags, or any additional text beyond that single sentence."
)
