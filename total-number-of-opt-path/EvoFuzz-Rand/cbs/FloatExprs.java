public class FloatExprs {

    public static float $gbvar;

    // Float.NaN optimization
    // jdk17/addnode.cpp Line 1429 - 1515
    public static float $mut0(float $lval1, float $lval2) {
        return (Math.max($lval1, $lval2) + Math.min($lval1, $lval2));
    }
    
    // Float.NaN optimization
    // jdk17/addnode.cpp Line 1429 - 1515
    public static float $mut1(float $lval1, float $lval2) {
        return (Math.max($lval1, Float.NaN) + Math.min(Float.NaN, $lval2));
    }

    // Float.NaN optimization
    // jdk17/addnode.cpp Line 1429 - 1515
    public static float $mut2(float $lval1, float $lval2, float $expr1) {
        return (Math.max($lval1, Float.NaN) + Math.min(($expr1), $lval2));
    }

    // Float.NaN optimization
    // jdk17/addnode.cpp Line 1429 - 1515
    public static float $mut3(float $lval1, float $lval2, float $expr1) {
        return (Math.max($lval1, ($expr1)) + Math.min(Float.NaN, $lval2));
    }

    // Try to trigger float constant division optimization
    // jdk17/divnode.cpp Line 740 - 749
    public static float $mut4(float $const1) {
        return ($const1 / 16.0f);
    }

    // Try to trigger float constant division optimization
    // jdk17/divnode.cpp Line 740 - 749
    public static float $mut5(float $const1) {
        return ($const1 / 8.0f);
    }

    // Try to trigger float constant modulus optimization
    // jdk17/divnode.cpp Line 1178 - 1217
    public static float $mut6() {
        return (15.656f % 4.32f);
    }

    public static void $mut7(float $expr1) {
        $gbvar = (($expr1) + 1.0f);
    }

    public static void $mut8(float $expr1) {
        $gbvar = (($expr1) + 0.1f);
    }

    public static void $mut9(float $expr1, float $expr2) {
        $gbvar = (($expr1) + ($expr2));
    }

    public static void $mut10(float $expr1, float $expr2) {
        $gbvar = (($expr1) + ($expr2));
    }

    public static float $mut20(float $expr1, float $expr2) {
        return (($expr1) + ($expr2));
    }

    public static float $mut21(float $expr1, float $expr2) {
        return (($expr1) - ($expr2));
    }

    public static float $mut22(float $expr1, float $expr2) {
        return (($expr1) * ($expr2));
    }

    public static float $mut23(float $expr1, float $expr2) {
        return (($expr1) / Math.max(1.0f, ($expr2)));
    }

    public static float $mut24(float $expr1) {
        return (($expr1));
    }

    public static float $mut25(float[] $lval1, int $expr1) {
        return $lval1[Math.abs($expr1 % AllFuzzerDefs.ARRAY_SIZE)];
    }

    public static void $mut26(float[] $lval1, int $expr1, float $expr2) {
        $lval1[Math.abs($expr1 % AllFuzzerDefs.ARRAY_SIZE)] = ($expr2);
    }

}
