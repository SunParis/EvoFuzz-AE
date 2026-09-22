public class MutatorMath {
    
    public static double $mut1(double $expr1) {
        return Math.sin($expr1);
    }

    public static double $mut2(double $expr1) {
        return Math.cos($expr1);
    }

    public static double $mut3(double $expr1) {
        return Math.tan($expr1);
    }

    public static double $mut4(double $expr1) {
        return Math.log(Math.abs($expr1));
    }

    public static double $mut5(double $expr1) {
        return Math.exp($expr1);
    }

    public static double $mut6(double $expr1) {
        return Math.sqrt(Math.abs($expr1));
    }

    public static double $mut7(double $expr1, double $expr2) {
        return Math.pow($expr1, $expr2);
    }

    public static double $mut8() {
        return Math.PI;
    }

}
