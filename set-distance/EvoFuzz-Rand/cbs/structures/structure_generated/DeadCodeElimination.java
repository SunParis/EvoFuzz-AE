class DeadCodeElimination {
    public static int $priority = 50;
    public static void $mut1(boolean $lval1, int $lval2, int $lval3, int $lval4, int $expr1) {
        $lval2 += $expr1;
        if ($lval1) {
            $lval2 += $expr1;
            $stmt();
        }
        $lval3 = $lval2 + $expr1;
        $lval4 = $lval2 - $expr1;
    }
    public static void $mut2(boolean $lval1, int $lval2, boolean $lval3, int $expr1) {
        $lval2 += $expr1;
        $lval3 = $lval1 && !$lval1;
        if ($lval3) {
            $lval2 += $expr1;
            $stmt();
        }
    }
}