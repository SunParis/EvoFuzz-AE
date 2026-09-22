class RegisterAllocation {
    
    public static int $priority = 50;
    
    public static void $mut1(int $lval1, int $lval2, int $lval3, int $lval4, int $lval5, int $lval6) {
        for($lval4 = 0; $lval4 < 333; $lval4++) {
            $lval5 = $lval1 + $lval2;
            $lval6 = $lval1 * $lval2 - $lval1;
            $lval3 = $lval5 + $lval6;
            $stmt();
        }
    }

}