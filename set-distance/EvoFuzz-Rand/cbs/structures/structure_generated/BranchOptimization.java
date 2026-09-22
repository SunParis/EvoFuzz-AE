class BranchOptimization {
    public static int $priority = 50;
    public static void $mut1(boolean $lval1, boolean $lval2, int $lval3, int $expr1) {
        $lval2 = $lval1 && !$lval1;
        if (!$lval2) {
            $lval3 *= $expr1;
            $stmt();
        }
        else {
            $lval3 += $expr1;
            $stmt();
        }
    }
}