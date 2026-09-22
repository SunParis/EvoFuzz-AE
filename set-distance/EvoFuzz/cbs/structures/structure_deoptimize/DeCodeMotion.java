class DeCodeMotion {
    public static int $priority = 50;
    public static int $gbvar1;
    public static void $mut1(int[] $lval1, int $lval2, int $expr1, int $lval3, int $lval4, int $lval5, int $lval6) {
        $lval2 = $expr1;
        $lval3 = 0;
        for($lval5 = 0; $lval5 < $lval4; $lval5++) {
            $lval3 += codeMotion_deoptimize($lval1, $lval6);
            $stmt();
        }
        $gbvar1 -= 113;
        $lval3 += codeMotion_deoptimize($lval1, $lval6);
    }

    public static int codeMotion_deoptimize(int[] codeMotion_deoptimize_array, int val) {
        int result = 0;
        for(int codeMotion_deoptimize_a = 0; codeMotion_deoptimize_a < codeMotion_deoptimize_array.length; codeMotion_deoptimize_a++) {
            result = codeMotion_deoptimize_array[val] * $gbvar1;
        }
        return result;
    }
    
}
