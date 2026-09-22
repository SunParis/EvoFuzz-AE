class ConstantFold {
    public static int $priority = 50;
    public static void $mut1(int $lval1, int $lval2, int $lval3, int $lval4) {
        for ($lval4 = 0; $lval4 < 333; $lval4++) {
            $lval1 = 34;
            $lval2 = 32878;
            $lval3 += $lval1 * 45 + $lval2;
            $stmt();
        }
    }
    public static void $mut2(double $lval1, double $lval2, double $lval3, int $lval4) {
        for ($lval4 = 0; $lval4 < 333; $lval4++) {
            $lval1 = 34.5;
            $lval2 = 32878.5;
            $lval3 += $lval1 * 45.5 + $lval2;
            $stmt();
        }
    }
    public static void $mut3(long $lval1, long $lval2, long $lval3, int $lval4) {
        for ($lval4 = 0; $lval4 < 333; $lval4++) {
            $lval1 = 34L;
            $lval2 = 32878L;
            $lval3 += $lval1 * 45L + $lval2;
            $stmt();
        }
    }
    public static void $mut4(float $lval1, float $lval2, float $lval3, int $lval4) {
        for ($lval4 = 0; $lval4 < 333; $lval4++) {
            $lval1 = 34.5f;
            $lval2 = 32878.5f;
            $lval3 += $lval1 * 45.5f + $lval2;
            $stmt();
        }
    }
    public static void $mut5(char $lval1, char $lval2, char $lval3, int $lval4) {
        for ($lval4 = 0; $lval4 < 333; $lval4++) {
            $lval1 = 'a';
            $lval2 = 'b';
            $lval3 = (char)($lval1 + $lval2);
            $stmt();
        }
    }
    public static void $mut6(boolean $lval1, boolean $lval2, boolean $lval3, int $lval4) {
        for ($lval4 = 0; $lval4 < 333; $lval4++) {
            $lval1 = true;
            $lval2 = false;
            $lval3 = $lval1 && $lval2;
            $stmt();
        }
    }
    public static void $mut7(String $lval1, String $lval2, String $lval3, int $lval4) {
        for ($lval4 = 0; $lval4 < 333; $lval4++) {
            $lval1 = "H";
            $lval2 = "Wo";
            $lval3 += $lval1 + $lval2;
            $stmt();
        }
    }
}