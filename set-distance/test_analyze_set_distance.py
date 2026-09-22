"""Focused checks for the manual AE set-distance report (no JVMs required)."""

import contextlib
import io
import json
from pathlib import Path
import tempfile
import unittest

import analyze_set_distance as report


class SetDistanceTests(unittest.TestCase):
    def setUp(self):
        self.temporary = tempfile.TemporaryDirectory()
        self.addCleanup(self.temporary.cleanup)
        self.root = Path(self.temporary.name)

    def trace(self, path, start=100, end=110, command="java -cp out/1 Test0001", paths=("1->2",)):
        path.parent.mkdir(parents=True, exist_ok=True)
        text = f"START_NS:::: {start}\n"
        if end is not None:
            text += f"END_NS:::: {end}\n"
        text += f"CMD_LINE:::: {command}\n"
        text += "File:::: make/hotspot/src/hotspot/share/opto/example.cpp\nFunction:::: f()\n"
        path.write_text(text + "\n".join(paths) + "\n")
        return path

    def seed(self, repo, system, paths=("1->2",)):
        relative = ("benchmarks/JavaFuzzer/allfuzzer-100" if system in report.FILTERED
                    else "seeds/allfuzzer-100")
        return self.trace(repo / relative / "Test0001.score", paths=paths)

    def mop_trace(self, repo, index, status="SUCCESS", start=100, end=110, paths=("1->3",)):
        mutant = repo / "mutants" / "Test0001_session" / str(index)
        mutant.mkdir(parents=True, exist_ok=True)
        if status is not None:
            (mutant / "route_data.log").write_text(status + "\nprogram output\n")
        command = (f"java -cp mutants/Test0001_session/{index}/:"
                   "mutants/Test0001_session/0/ Test0001")
        return self.trace(repo / "out" / f"{index}_route_data.log", start, end, command, paths)

    def test_symmetric_difference_not_total_or_accumulation(self):
        repo = self.root / "EvoFuzz"
        self.seed(repo, "EvoFuzz", ("1->2", "1->3", "1->3"))
        self.trace(repo / "out/success/1/route_data.log", paths=("1->2", "1->4"))
        self.trace(repo / "out/success/2/route_data.log", paths=("1->2", "1->3"))
        result = report.analyze("EvoFuzz", repo)
        self.assertEqual(result.distances, [2, 0])

    def test_preserves_order_and_repeated_line_numbers(self):
        path = self.trace(self.root / "trace.log", paths=("1->4->2->4", "1->4", "1->4"))
        paths = report.load_paths(path)
        self.assertEqual(len(paths), 2)
        self.assertIn(("opto/example.cpp", "f()", (1, 4, 2, 4)), paths)

    def test_json_and_raw_paths_match_despite_build_prefix(self):
        raw = self.trace(self.root / "trace.log")
        converted = self.root / "seed.score.json"
        converted.write_text(json.dumps({"src/hotspot/share/opto/example.cpp": {"f()": ["1->2", "1->2"]}}))
        self.assertEqual(report.load_paths(raw), report.load_paths(converted))

    def test_filters_both_mop_variants_and_current_mutant_status(self):
        for system in report.FILTERED:
            with self.subTest(system=system):
                repo = self.root / system
                self.seed(repo, system)
                fallback = repo / "mutants/Test0001_session/0"
                fallback.mkdir(parents=True)
                (fallback / "route_data.log").write_text("SUCCESS\n")
                self.mop_trace(repo, 1)
                self.mop_trace(repo, 2, "FAILED")
                self.mop_trace(repo, 3, "TIMEOUT")
                self.mop_trace(repo, 4, None)
                self.mop_trace(repo, 5, "unexpected")
                result = report.analyze(system, repo)
                self.assertEqual(result.distances, [2])
                for key in ("failed", "timeout", "missing_status", "unknown_status"):
                    self.assertEqual(result.counts[key], 1)

    def test_cutoff_is_end_time_inclusive_and_before_status_filtering(self):
        repo = self.root / "MopFuzzer"
        self.seed(repo, "MopFuzzer")
        cutoff = 100 + report.NS_PER_HOUR * 12
        self.mop_trace(repo, 1, "FAILED", start=100, end=200)
        self.mop_trace(repo, 2, start=cutoff - 100, end=cutoff)
        self.mop_trace(repo, 3, start=cutoff - 50, end=cutoff + 1)
        result = report.analyze("MopFuzzer", repo)
        self.assertEqual(result.distances, [2])
        self.assertEqual(result.counts["outside_window"], 1)

    def test_incomplete_early_trace_does_not_extend_window(self):
        repo = self.root / "EvoFuzz"
        self.seed(repo, "EvoFuzz")
        self.trace(repo / "out/timed_out/1/route_data.log", start=100, end=None)
        cutoff = 100 + report.NS_PER_HOUR
        self.trace(repo / "out/success/2/route_data.log", start=200, end=300)
        self.trace(repo / "out/success/3/route_data.log", start=cutoff - 1, end=cutoff + 1)
        result = report.analyze("EvoFuzz", repo, hours=1)
        self.assertEqual(result.distances, [0])
        self.assertEqual(result.counts["incomplete"], 1)
        self.assertEqual(result.counts["outside_window"], 1)

    def test_original_seed_used_instead_of_promoted_mutant(self):
        repo = self.root / "EvoFuzz-Rand"
        self.seed(repo, "EvoFuzz-Rand")
        self.trace(repo / "seeds/new_seeds/0/Test0001.score", paths=("1->3",))
        self.trace(repo / "out/success/1/route_data.log", paths=("1->3",))
        self.assertEqual(report.analyze("EvoFuzz-Rand", repo).distances, [2])

    def test_arbitrary_absolute_recorded_classpath_is_rebased(self):
        classpath, seed = report.command_info(
            "java --class-path=/old/place/mutants/Test0001_session/2:/old/place/mutants/Test0001_session/0 Test0001")
        self.assertEqual(seed, "Test0001")
        self.assertEqual(report.status_file(classpath, self.root),
                         self.root / "mutants/Test0001_session/2/route_data.log")
        with self.assertRaises(ValueError):
            report.status_file("mutants/../../private/1", self.root)

    def test_missing_seed_is_error_not_zero_distance(self):
        repo = self.root / "EvoFuzz"
        self.trace(repo / "out/success/1/route_data.log")
        with self.assertRaisesRegex(ValueError, "missing original seed trace"):
            report.analyze("EvoFuzz", repo)

    def test_custom_seed_directory(self):
        repo = self.root / "EvoFuzz"
        self.trace(repo / "out/success/1/route_data.log")
        self.trace(self.root / "custom/Test0001.score")
        result = report.analyze("EvoFuzz", repo, seed_dir=self.root / "custom")
        self.assertEqual(result.distances, [0])

    def test_empty_path_set_is_distinct_from_missing_trace(self):
        repo = self.root / "EvoFuzz"
        self.seed(repo, "EvoFuzz")
        self.trace(repo / "out/success/1/route_data.log", paths=())
        self.assertEqual(report.analyze("EvoFuzz", repo).distances, [1])

    def test_main_class_as_recorded_class_file(self):
        self.assertEqual(report.command_info("java -cp /old/out/16 /old/out/16/Test0009.class"),
                         ("/old/out/16", "Test0009"))

    def test_artemis_pairs_by_reference_id_not_just_seed_name(self):
        repo = self.root / "Artemis"
        for index, paths in ((1, ("1->2",)), (2, ("1->3",))):
            self.trace(repo / f"out/out-log/ref{index}_route_data.log", start=100, end=110,
                       command=f"java -cp /old/artemi/../out/jaf/{index} Test0001", paths=paths)
            self.trace(repo / f"out/out-log/mut{index}_route_data.log", start=120, end=130,
                       command=f"java -cp /old/out/jaf/{index}/mutants/0 Test0001", paths=("1->2",))
        result = report.analyze("Artemis", repo)
        self.assertEqual(result.distances, [0, 2])
        self.assertEqual(result.counts["reference"], 2)

    def test_artemis_reference_only_is_not_a_distribution(self):
        repo = self.root / "Artemis"
        self.trace(repo / "out/out-log/ref_route_data.log", command="java -cp out/jaf/1 Test0001")
        with self.assertRaisesRegex(ValueError, "Artemis needs both reference and mutant"):
            report.analyze("Artemis", repo)

    def test_artemis_missing_or_late_reference_is_error(self):
        repo = self.root / "Artemis"
        self.trace(repo / "out/out-log/mut_route_data.log", command="java -cp out/jaf/1/mutants/0 Test0001")
        with self.assertRaisesRegex(ValueError, "expected one completed reference"):
            report.analyze("Artemis", repo)
        self.trace(repo / "out/out-log/ref_route_data.log", start=200, end=210,
                   command="java -cp out/jaf/1 Test0001")
        with self.assertRaisesRegex(ValueError, "expected one completed reference"):
            report.analyze("Artemis", repo)

    def test_hours_bounds(self):
        for hours in (0, -1, 12.001, float("inf"), float("nan")):
            with self.subTest(hours=hours), self.assertRaises(ValueError):
                report.window_ns(hours)
        self.assertEqual(report.window_ns(0.5), report.NS_PER_HOUR // 2)

    def test_invalid_path_is_not_silently_accepted(self):
        path = self.trace(self.root / "trace.log", paths=("not a path",))
        with self.assertRaisesRegex(ValueError, "invalid optimization path"):
            report.load_paths(path)

    def test_cli_writes_only_pdf_and_preserves_inputs(self):
        repo = self.root / "MopFuzzer"
        self.seed(repo, "MopFuzzer")
        self.mop_trace(repo, 1)
        before = {p: p.read_bytes() for p in repo.rglob("*") if p.is_file()}
        with contextlib.redirect_stdout(io.StringIO()):
            self.assertEqual(report.main(["MopFuzzer", "--root", str(self.root)]), 0)
        pdf = repo / "set_distance.pdf"
        self.assertTrue(pdf.read_bytes().startswith(b"%PDF-"))
        after = {p: p.read_bytes() for p in repo.rglob("*") if p.is_file() and p != pdf}
        self.assertEqual(before, after)

    def test_all_continues_after_artemis_has_no_samples(self):
        for system in report.SYSTEMS:
            if system == "Artemis":
                continue
            repo = self.root / system
            self.seed(repo, system)
            if system in report.FILTERED:
                self.mop_trace(repo, 1)
            else:
                self.trace(repo / "out/success/1/route_data.log")
        with contextlib.redirect_stdout(io.StringIO()), contextlib.redirect_stderr(io.StringIO()):
            self.assertEqual(report.main(["all", "--root", str(self.root)]), 1)
        self.assertEqual(len(list(self.root.glob("*/set_distance.pdf"))), 4)


if __name__ == "__main__":
    unittest.main()
