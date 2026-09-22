class CSE {
    public static int $priority = 50;
    public static void $mut1(int $lval1, int $lval2, int $expr1, int $expr2, int $lval3, int $lval4) {
        for ($lval4 = 0; $lval4 < 333; $lval4++) {
            $lval1 = ($expr1 + $expr2) * ($expr1 + $expr2) + ($expr1 + $expr2);
            $lval2 = $expr1 + $expr2;
            $lval3 += $lval1 + $lval2;
            $stmt();
        }
    }
    public static void $mut2(short $lval1, short $lval2, short $expr1, short $expr2, short $lval3, int $lval4) {
        for ($lval4 = 0; $lval4 < 333; $lval4++) {
            $lval1 = (short)(($expr1 + $expr2) + ($expr1 + $expr2));
            $lval2 = (short)($expr1 + $expr2);
            $lval3 += (short)($lval1 + $lval2);
            $stmt();
        }
    }
    public static void $mut3(long $lval1, long $lval2, long $expr1, long $expr2, long $lval3, int $lval4) {
        for ($lval4 = 0; $lval4 < 333; $lval4++) {
            $lval1 = ($expr1 + $expr2) + ($expr1 + $expr2);
            $lval2 = $expr1 + $expr2;
            $lval3 += $lval1 + $lval2;
            $stmt();
        }
    }
    public static void $mut4(float $lval1, float $lval2, float $expr1, float $expr2, float $lval3, int $lval4) {
        for ($lval4 = 0; $lval4 < 333; $lval4++) {
            $lval1 = ($expr1 + $expr2) + ($expr1 + $expr2);
            $lval2 = $expr1 + $expr2;
            $lval3 += $lval1 + $lval2;
            $stmt();
        }
    }
    public static void $mut5(double $lval1, double $lval2, double $expr1, double $expr2, double $lval3, int $lval4) {
        for ($lval4 = 0; $lval4 < 333; $lval4++) {
            $lval1 = ($expr1 + $expr2) + ($expr1 + $expr2);
            $lval2 = $expr1 + $expr2;
            $lval3 += $lval1 + $lval2;
            $stmt();
        }
    }
    public static void $mut6(char $lval1, char $lval2, char $expr1, char $expr2, char $lval3, int $lval4) {
        for ($lval4 = 0; $lval4 < 333; $lval4++) {
            $lval1 = (char)(($expr1 + $expr2) + 2 * ($expr1 + $expr2));
            $lval2 = (char)($expr1 + $expr2);
            $lval3 += (char)($lval1 + $lval2);
            $stmt();
        }
    }

}