class ZeroOffsetElimination {
    public static int $priority = 50;
    public static void $mut1(int $lval1, int $lval2, int $lval3) {
        $lval2 = $lval1 / $lval3;
        if($lval3 != 0) {
            $lval2 = $lval2 + $lval3;
            $stmt();
        }
        else {
            $lval2 = $lval2 - $lval3;
            $stmt();
        }
    }
}