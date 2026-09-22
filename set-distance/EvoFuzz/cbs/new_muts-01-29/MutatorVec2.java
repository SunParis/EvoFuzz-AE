import java.util.Vector;

public class MutatorVec2 {

    public static void $mut1(java.util.Vector<Integer> $lval1, int $expr1, int $expr2) {
        $lval1.set(Math.abs($expr1) % $lval1.size(), Integer.valueOf($expr2));
    }

    public static void $mut2(java.util.Vector<Long> $lval1, int $expr1, long $expr2) {
        $lval1.set(Math.abs($expr1) % $lval1.size(), Long.valueOf($expr2));
    }

    public static void $mut3(java.util.Vector<Double> $lval1, int $expr1, double $expr2) {
        $lval1.set(Math.abs($expr1) % $lval1.size(), Double.valueOf($expr2));
    }

    public static void $mut4(java.util.Vector<Float> $lval1, int $expr1, float $expr2) {
        $lval1.set(Math.abs($expr1) % $lval1.size(), Float.valueOf($expr2));
    }

    public static void $mut5(java.util.Vector<Short> $lval1, int $expr1, short $expr2) {
        $lval1.set(Math.abs($expr1) % $lval1.size(), Short.valueOf($expr2));
    }

    public static void $mut6(java.util.Vector<Byte> $lval1, int $expr1, byte $expr2) {
        $lval1.set(Math.abs($expr1) % $lval1.size(), Byte.valueOf($expr2));
    }

    public static void $mut7(java.util.Vector<String> $lval1, int $expr1, String $expr2) {
        $lval1.set(Math.abs($expr1) % $lval1.size(), $expr2);
    }

    public static void $mut8(java.util.Vector<Object> $lval1, int $expr1, Object $expr2) {
        $lval1.set(Math.abs($expr1) % $lval1.size(), $expr2);
    }

}
