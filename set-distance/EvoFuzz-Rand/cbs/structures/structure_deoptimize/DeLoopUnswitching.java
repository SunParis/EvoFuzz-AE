public class DeLoopUnswitching{
    public static int $priority = 50;
    public static int $gbvar1;
    
    public static void $mut1(int $lval1, int $expr1, int $expr2, int $lval2, int $expr3, int $lval3, int $lval4) { 
        $gbvar1 = 100;
        for($lval1 = 0; $lval1 < ($expr1 + $expr2 + $gbvar1); $lval1++) {
            $lval2 += loopUnswitching_deoptimize($lval4, $lval3);
            $stmt();
        }
        $gbvar1 = -100;
        $lval2 += loopUnswitching_deoptimize($lval4, $lval3);
    }

    public static int loopUnswitching_deoptimize(int loopUnswitching_deoptimize_a, int loopUnswitching_deoptimize_b) {
        int loopUnswitching_deoptimize_result = 0;
        for(int loopUnswitching_deoptimize_tmp = 0; loopUnswitching_deoptimize_tmp < 333; loopUnswitching_deoptimize_tmp++) {
            if ($gbvar1 > 0) {
                 loopUnswitching_deoptimize_result = loopUnswitching_deoptimize_a + loopUnswitching_deoptimize_b;
            }
            else {
                loopUnswitching_deoptimize_result = loopUnswitching_deoptimize_a - loopUnswitching_deoptimize_b;
            }
        }
        return loopUnswitching_deoptimize_result;
    }
}
