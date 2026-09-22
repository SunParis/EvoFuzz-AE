class DeEscapeAnalysis {
    public static int $priority = 50;
    public static $cls $gbvar1;
    public static void $mut1(int $lval1, int $lval2, int $lval3, int $lval4) {
        for ($lval1 = 0; $lval1 < 333; $lval1++) {
            $lval4 += escapeAnalysis_deoptimize_1($lval1, $lval2,1);
            $stmt();
        }
        $lval4 += escapeAnalysis_deoptimize_1($lval1, $lval2, 2);
    }
    public static int escapeAnalysis_deoptimize_1(int escapeAnalysis_deoptimize_1_a, int escapeAnalysis_deoptimize_1_b, int escapeAnalysis_deoptimize_1_c) {
        $cls $tmp1 = new $cls();
        $tmp1.x = escapeAnalysis_deoptimize_1_a;
        $tmp1.y = escapeAnalysis_deoptimize_1_b;
        if (escapeAnalysis_deoptimize_1_c != 1) {
            $gbvar1 = $tmp1;
        }
        return $tmp1.x + $tmp1.y;
    }

    public static $cls $new1() {
        return new $cls();
    }

    public static void $check1($cls $lval1) {
        System.out.println("$lval1.x: " + $lval1.x + ", $lval1.y: " + $lval1.y);
    }

}
class $cls{
    int x, y;
}