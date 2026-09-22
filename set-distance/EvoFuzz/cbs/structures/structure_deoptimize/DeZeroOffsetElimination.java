class DeZeroOffsetElimination {

    public static int $priority = 50;

    public static void $mut1(int $lval1, int $lval2, int $lval3) {
        for($lval3 = 1; $lval3 < 333; $lval3++) {
            $lval1 += zeroOffsetElimination_deoptimize($lval2, $lval3);
            $stmt();
        }
        $lval2 = 0;
        $lval1 += zeroOffsetElimination_deoptimize($lval2, $lval3);

    }

    public static int zeroOffsetElimination_deoptimize(int zeroOffsetElimination_deoptimize_a, int zeroOffsetElimination_deoptimize_b) {
        int zeroOffsetElimination_deoptimize_result = 0;
        zeroOffsetElimination_deoptimize_result = zeroOffsetElimination_deoptimize_a / zeroOffsetElimination_deoptimize_b;
        
        if(zeroOffsetElimination_deoptimize_b != 0) {
            zeroOffsetElimination_deoptimize_result = zeroOffsetElimination_deoptimize_result + zeroOffsetElimination_deoptimize_b;
        }
        else {
            zeroOffsetElimination_deoptimize_result = zeroOffsetElimination_deoptimize_result - zeroOffsetElimination_deoptimize_b;
        }
        return zeroOffsetElimination_deoptimize_result;
    }

}