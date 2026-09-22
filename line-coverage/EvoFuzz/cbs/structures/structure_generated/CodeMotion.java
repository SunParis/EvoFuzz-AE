class CodeMotion {
    public static int $priority = 50;
    public static void $mut1(int[] $lval1, int $lval2, int $expr1, int $lval3, int $lval4, int $lval5) {
        $lval2 = $expr1;
        $lval3 = 0;
        for($lval4 = 0; $lval4 < $lval1.length; $lval4++) {
            $lval3 = $lval1[$lval5] * $lval2;
            $stmt();
        }
    }
    
}