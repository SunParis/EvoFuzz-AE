import importlib.util
from pathlib import Path
import subprocess
import sys
import tempfile
import unittest

from analyze_opt_paths import (
    NS_PER_HOUR, SYSTEMS, analyze, count_successful_paths, iter_paths,
    last_printed_total, normalize_path, recorded_total, status_file, window_ns,
)


BODY = """File:::: make/hotspot/src/hotspot/share/opto/example.cpp
Function:::: Example::run()
1->2->2->1->3
File:::: src/hotspot/share/opto/example.cpp
Function:::: Example::run()
1->2->3
"""


class OptPathTests(unittest.TestCase):
    def setUp(self):
        self.directory = tempfile.TemporaryDirectory()
        self.addCleanup(self.directory.cleanup)
        self.root = Path(self.directory.name)
        self.repo = self.root / "MopFuzzer"
        (self.repo / "out").mkdir(parents=True)
        (self.repo / "mutants").mkdir()

    def make_trace(self, number, status="SUCCESS", body=BODY, command=None):
        case = self.repo / "mutants" / "Test0001" / str(number)
        case.mkdir(parents=True, exist_ok=True)
        if status is not None:
            (case / "route_data.log").write_text(status + "\nprogram output\n")
        if command is None:
            command = f'java -cp "mutants/Test0001/{number}/:mutants/Test0001/0/" Test0001'
        trace = self.repo / "out" / f"{number}_route_data.log"
        trace.write_text(f"START_NS:::: 10\nEND_NS:::: 20\nCMD_LINE:::: {command}\n" + body)
        return trace

    def test_last_printed_total_not_maximum_or_sum(self):
        repo = self.root / "EvoFuzz"
        repo.mkdir()
        (repo / "run.log").write_text(
            "[INFO] Total paths recorded: 999\n"
            "[INFO] new_path_num=(3, 800, 1200)\n"
            "[INFO] Total paths recorded: 0\n"
        )
        result = last_printed_total("EvoFuzz", repo)
        self.assertEqual((result.total, result.line_number), (0, 3))
        self.assertEqual(last_printed_total("EvoFuzz-Rand", repo).total, 0)

    def test_artemis_final_total(self):
        (self.repo / "out/out.log").write_text(
            "* Total unique path num: 1234\n* Total unique path num: 5678\n"
        )
        self.assertEqual(last_printed_total("Artemis", self.repo).total, 5678)

    def test_no_total_is_an_error_not_zero(self):
        (self.repo / "run.log").write_text("No final summary yet\n")
        with self.assertRaisesRegex(ValueError, "no final path total"):
            last_printed_total("EvoFuzz", self.repo)

    def test_only_successful_traces_contribute(self):
        self.make_trace(1)
        self.make_trace(2, "FAILED\nSUCCESS", BODY.replace("1->2->3", "50->60"))
        self.make_trace(3, "TIMEOUT", BODY.replace("1->2->3", "70->80"))
        self.make_trace(4, None)
        self.make_trace(5, "Command Execution Internal Exception: interrupted")
        self.make_trace(6, "")
        incomplete = self.make_trace(7)
        incomplete.write_text(incomplete.read_text().replace("END_NS:::: 20\n", ""))
        self.make_trace(8, command="java Test0001")
        result = count_successful_paths("MopFuzzer", self.repo)
        self.assertEqual(result.total, 1)
        self.assertEqual(dict(result.traces), {
            "found": 8, "success": 1, "failed": 1, "timeout": 1,
            "missing_status": 1, "unknown_status": 2, "incomplete_header": 1,
            "unmatched_command": 1,
        })
        self.assertEqual(count_successful_paths("EvoFuzz-Coarse", self.repo).total, 1)

    def test_paths_are_globally_deduplicated(self):
        self.make_trace(1)
        self.make_trace(2)
        self.make_trace(3, body=BODY.replace("Example::run()", "Other::run()"))
        self.make_trace(4, body=BODY.replace("example.cpp", "other.cpp"))
        result = count_successful_paths("MopFuzzer", self.repo)
        self.assertEqual(result.total, 3)
        self.assertEqual(result.traces["success"], 4)

    def test_zero_successes_with_known_failures_is_zero(self):
        self.make_trace(1, "FAILED")
        self.make_trace(2, "TIMEOUT")
        self.assertEqual(count_successful_paths("MopFuzzer", self.repo).total, 0)

    def test_missing_evidence_is_an_error(self):
        with self.assertRaisesRegex(ValueError, "no .*route_data.log"):
            count_successful_paths("MopFuzzer", self.repo)
        self.make_trace(1, None)
        with self.assertRaisesRegex(ValueError, "no traces have a verifiable"):
            count_successful_paths("MopFuzzer", self.repo)

    def test_status_resolves_first_classpath_entry_and_relocated_absolute_paths(self):
        expected = self.repo / "mutants/Test0001/2/route_data.log"
        for command in (
            'java -cp "mutants/Test0001/2/:mutants/Test0001/0/" Test0001',
            'java -classpath "/old/repo/mutants/Test0001/2/:/old/repo/mutants/Test0001/0/" Test0001',
            'java --class-path=./mutants/Test0001/2/:other Test0001',
        ):
            with self.subTest(command=command):
                self.assertEqual(status_file(command, self.repo), expected)
        for command in ('java Test', 'java -cp ../mutants/Test0001/2 Test',
                        'java -cp mutants/Test0001/../../other Test', 'java -cp "unterminated'):
            with self.subTest(command=command), self.assertRaises(ValueError):
                status_file(command, self.repo)

    def test_path_identity_and_normalization(self):
        self.assertEqual(normalize_path("4->4->2->8->-1->10->"), (4, 8, 10))
        paths = set(iter_paths((BODY + """File:::: src/hotspot/share/opto/another.cpp
5->6
Function:::: Other::run()
7->8
File:::: src/hotspot/share/notopto/example.cpp
Function:::: Example::run()
100->200
""").splitlines()))
        self.assertEqual(paths, {
            ("opto/example.cpp", "Example::run()", (1, 2, 3)),
            ("opto/another.cpp", "Other::run()", (7, 8)),
        })

    def test_time_window_boundary_and_failures_do_not_shift_origin(self):
        start = 100
        cutoff = start + 12 * NS_PER_HOUR
        early = self.make_trace(1, "FAILED")
        early.write_text(early.read_text().replace("START_NS:::: 10", f"START_NS:::: {start}")
                         .replace("END_NS:::: 20", f"END_NS:::: {start + 10}"))
        for number, end in ((2, cutoff), (3, cutoff + 1)):
            trace = self.make_trace(number, body=BODY.replace("Example::run()", f"F{number}()"))
            trace.write_text(trace.read_text().replace("START_NS:::: 10", f"START_NS:::: {cutoff - 10}")
                             .replace("END_NS:::: 20", f"END_NS:::: {end}"))
        result = count_successful_paths("MopFuzzer", self.repo)
        self.assertEqual(result.total, 1)
        self.assertEqual(result.start_ns, start)
        self.assertEqual(result.traces["outside_window"], 1)
        self.assertEqual(count_successful_paths("MopFuzzer", self.repo, 6).total, 0)

    def test_timestamped_totals_cap_all_three_printed_systems(self):
        start = 100
        cutoff = start + 12 * NS_PER_HOUR
        for system in ("EvoFuzz", "EvoFuzz-Rand", "Artemis"):
            with self.subTest(system=system):
                repo = self.root / system
                (repo / "out").mkdir(parents=True)
                log = repo / ("out/out.log" if system == "Artemis" else "run.log")
                prefix = "* Total unique path num:" if system == "Artemis" else "Total paths recorded:"
                log.write_text(f"{prefix} 30\n")
                recording = repo / ("out/path_rec_mopfuzzer_123.txt" if system == "Artemis" else "out/path_rec.txt")
                records = []
                for end, new in ((cutoff, 10), (cutoff + 1, 20)):
                    counts = (new, new) if system == "Artemis" else (new, new, 30)
                    records.append(f"({start}, {end})\t{counts}\n")
                recording.write_text("".join(records))
                result = recorded_total(system, repo, 12)
                self.assertEqual(result.total, 10)
                self.assertEqual(result.source, recording)
                self.assertEqual(result.traces["outside_window"], 1)
                self.assertEqual(recorded_total(system, repo, 6).total, 0)

    def test_invalid_intervals_are_rejected(self):
        for hours in (0, -1, 12.001, 13, float("nan"), float("inf")):
            with self.subTest(hours=hours), self.assertRaises(ValueError):
                window_ns(hours)

    def test_short_run_requires_consistent_final_total(self):
        (self.repo / "run.log").write_text("Total paths recorded: 99\n")
        recording = self.repo / "out/path_rec.txt"
        recording.write_text("(10, 20)\t(3, 3, 3)\n")
        with self.assertRaisesRegex(ValueError, "disagrees"):
            recorded_total("EvoFuzz", self.repo, 12)
        recording.write_text("malformed\n")
        with self.assertRaisesRegex(ValueError, "malformed record"):
            recorded_total("EvoFuzz", self.repo, 12)

    @unittest.skipUnless(importlib.util.find_spec("matplotlib"), "Matplotlib is not installed")
    def test_all_systems_cli_writes_only_pdfs_and_preserves_inputs(self):
        self.make_trace(1)
        for system in ("EvoFuzz", "EvoFuzz-Rand", "Artemis", "EvoFuzz-Coarse"):
            repo = self.root / system
            (repo / "out").mkdir(parents=True)
            if system.startswith("EvoFuzz") and system != "EvoFuzz-Coarse":
                (repo / "run.log").write_text("Total paths recorded: 4161\n")
                (repo / "out/path_rec.txt").write_text("(10, 20)\t(4161, 4161, 4161)\n")
            elif system == "Artemis":
                (repo / "out/out.log").write_text("* Total unique path num: 6429\n")
                (repo / "out/path_rec_mopfuzzer_123.txt").write_text("(10, 20) (6429, 6429)\n")
            else:
                previous = self.repo
                self.repo = repo
                self.make_trace(1)
                self.repo = previous
        inputs = {path: path.read_bytes() for path in self.root.rglob("*") if path.is_file()}
        output = self.root / "pdfs"
        script = Path(__file__).with_name("analyze_opt_paths.py")
        result = subprocess.run(
            [sys.executable, "-B", str(script), "all", "--root", str(self.root),
             "--output-dir", str(output), "--seed-baseline", "8327"],
            capture_output=True, text=True,
        )
        self.assertEqual(result.returncode, 0, result.stderr)
        self.assertEqual(sorted(p.name for p in output.iterdir()),
                         sorted(f"{system}-total-opt-paths.pdf" for system in SYSTEMS))
        for path in output.iterdir():
            self.assertTrue(path.read_bytes().startswith(b"%PDF-"))
        for path, data in inputs.items():
            self.assertEqual(path.read_bytes(), data)
        self.assertEqual(analyze("EvoFuzz", self.root).total, 4161)


if __name__ == "__main__":
    unittest.main()
