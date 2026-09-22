import importlib.util
from pathlib import Path
import subprocess
import sys
import tempfile
import unittest
import warnings

from analyze_line_coverage import read_coverage


FIXTURE = """TN:first
SF:/jdk/src/hotspot/share/runtime/runtime.cpp
DA:1,2
DA:2,0
LF:2
LH:1
end_of_record
TN:second
SF:/jdk/src/hotspot/share/runtime/runtime.cpp
DA:1,0
DA:2,3,checksum
end_of_record
SF:/jdk/src/hotspot/share/c1/c1.cpp
DA:10,0
DA:11,2
end_of_record
SF:/jdk/src/hotspot/share/opto/c2.cpp
DA:20,8
end_of_record
SF:/jdk/src/hotspot/share/gc/example/c1/other.cpp
DA:5,0
end_of_record
SF:/usr/include/example.h
DA:1,0
end_of_record
"""


class CoverageTests(unittest.TestCase):
    def setUp(self):
        self.directory = tempfile.TemporaryDirectory()
        self.addCleanup(self.directory.cleanup)
        self.info = Path(self.directory.name) / "coverage.info"

    def read(self, text):
        self.info.write_text(text)
        return read_coverage(self.info)

    def test_deduplication_and_summary_scope(self):
        results = self.read(FIXTURE)
        self.assertEqual(
            [(item.module, item.covered, item.total) for item in results],
            [("Runtime", 2, 2), ("C1", 1, 2), ("C2", 1, 1), ("Summary", 4, 7)],
        )
        self.assertAlmostEqual(results[-1].percentage, 100 * 4 / 7)

    def test_negative_count_does_not_cancel_a_positive_duplicate(self):
        with warnings.catch_warnings(record=True) as caught:
            warnings.simplefilter("always")
            results = self.read(FIXTURE.replace("DA:1,0\nDA:2,3", "DA:1,-100\nDA:2,3"))
        self.assertEqual(results[0].covered, 2)
        self.assertIn("negative DA", str(caught[0].message))

    def test_missing_module_fails_instead_of_reporting_zero(self):
        with self.assertRaisesRegex(ValueError, "no executable lines found for C2"):
            self.read(FIXTURE.replace("/share/opto/", "/share/not-opto/"))

    def test_malformed_or_truncated_input(self):
        for text in ("", "DA:1,1\n", "SF:x\nDA:1,x\n", "SF:x\nDA:0,1\n",
                     "SF:x\nDA:1,1\n", "SF:x\nSF:y\n", "SF:\n"):
            with self.subTest(text=text), self.assertRaises(ValueError):
                self.read(text)

    def test_zero_hit_lines_are_valid(self):
        results = self.read(FIXTURE.replace("DA:20,8", "DA:20,0"))
        self.assertEqual(results[2].percentage, 0)

    @unittest.skipUnless(importlib.util.find_spec("matplotlib"), "Matplotlib is not installed")
    def test_cli_writes_only_a_pdf_and_preserves_input(self):
        self.info.write_text(FIXTURE)
        result = subprocess.run(
            [sys.executable, str(Path(__file__).with_name("analyze_line_coverage.py")),
             str(self.info), "--system", "EvoFuzz"], capture_output=True, text=True,
        )
        self.assertEqual(result.returncode, 0, result.stderr)
        self.assertEqual(self.info.read_text(), FIXTURE)
        self.assertEqual(sorted(path.name for path in self.info.parent.iterdir()),
                         ["coverage.info", "coverage.pdf"])
        self.assertTrue(self.info.with_suffix(".pdf").read_bytes().startswith(b"%PDF-"))


if __name__ == "__main__":
    unittest.main()
