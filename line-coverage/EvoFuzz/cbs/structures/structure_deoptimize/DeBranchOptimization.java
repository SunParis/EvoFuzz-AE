class DeBranchOptimization {
    
    public static int $priority = 50;
    
    public static boolean $gbvar1;
    
    public static void $mut1(boolean $lval1, int $lval2, int $lval3, int $expr1, int $lval4) {
        $gbvar1 = $lval1 && !$lval1;
        for ($lval4 = 0; $lval4 < $expr1; $lval4++) {
            $lval3 += branchOptimization_deoptimize($lval2, $expr1);
            $stmt();
        }
        $gbvar1 = true;
        $lval3 += branchOptimization_deoptimize($lval2, $expr1);
    }

    public static int branchOptimization_deoptimize(int branchOptimization_deoptimize_a, int branchOptimization_deoptimize_b) {
        if ($gbvar1) {
            return branchOptimization_deoptimize_a * branchOptimization_deoptimize_b;
        }
        else {
            return branchOptimization_deoptimize_a + branchOptimization_deoptimize_b;
        }
    }
}
