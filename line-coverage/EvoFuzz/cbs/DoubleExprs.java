public class DoubleExprs {

    public static double $gbvar;
    
    // Double.NaN optimization
    // jdk17/addnode.cpp Line 1429 - 1515
    public static double $mut0(double $lval1, double $lval2) {
        return (Math.max($lval1, $lval2) + Math.min($lval1, $lval2));
    }
    
    // Double.NaN optimization
    // jdk17/addnode.cpp Line 1429 - 1515
    public static double $mut1(double $lval1, double $lval2) {
        return (Math.max($lval1, Double.NaN) + Math.min(Double.NaN, $lval2));
    }

    // Double.NaN optimization
    // jdk17/addnode.cpp Line 1429 - 1515
    public static double $mut2(double $lval1, double $lval2, double $expr1) {
        return (Math.max($lval1, Double.NaN) + Math.min(($expr1), $lval2));
    }

    // Double.NaN optimization
    // jdk17/addnode.cpp Line 1429 - 1515
    public static double $mut3(double $lval1, double $lval2, double $expr1) {
        return (Math.max($lval1, ($expr1)) + Math.min(Double.NaN, $lval2));
    }

    // Try to trigger division optimization
    // jdk17/divnode.cpp Line 832 - 841
    public static double $mut4(double $const1) {
        return ($const1 / 16.0);
    }

    // Try to trigger division optimization
    // jdk17/divnode.cpp Line 832 - 841
    public static double $mut5(double $const1) {
        return ($const1 / 8.0);
    }

    // Try to trigger remainder optimization
    // jdk17/divnode.cpp Line 1220 - 1260
    public static double $mut6() {
        return (1523.56656 % 446546.45632);
    }

    // Try to trigger remainder optimization
    // jdk17/divnode.cpp Line 1220 - 1260
    public static double $mut7() {
        return (1234.45656 % 7846.4569);
    }

    public static void $mut8(double $expr1) {
        $gbvar = (($expr1) + 1.0f);
    }

    public static void $mut9(double $expr1) {
        $gbvar = (($expr1) + 0.1f);
    }

    public static void $mut10(double $expr1, double $expr2) {
        $gbvar = (($expr1) + ($expr2));
    }

    public static void $mut11(double $expr1, double $expr2) {
        $gbvar = (($expr1) + ($expr2));
    }

    public static double $mut20(double $expr1, double $expr2) {
        return (($expr1) + ($expr2));
    }

    public static double $mut21(double $expr1, double $expr2) {
        return (($expr1) - ($expr2));
    }

    public static double $mut22(double $expr1, double $expr2) {
        return (($expr1) * ($expr2));
    }

    public static double $mut23(double $expr1, double $expr2) {
        return (($expr1) / Math.max(1.0, ($expr2)));
    }

    public static double $mut24(double $expr1) {
        return (($expr1));
    }

    public static double $mut25(double[] $lval1, int $expr1) {
        return $lval1[Math.abs($expr1 % AllFuzzerDefs.ARRAY_SIZE)];
    }

    public static void $mut26(double[] $lval1, int $expr1, double $expr2) {
        $lval1[Math.abs($expr1 % AllFuzzerDefs.ARRAY_SIZE)] = ($expr2);
    }

}
