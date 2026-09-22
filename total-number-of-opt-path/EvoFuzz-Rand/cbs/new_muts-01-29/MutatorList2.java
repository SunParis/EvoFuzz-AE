import java.util.List;
import java.util.ArrayList;

public class MutatorList2 {

    public static void $mut1(java.util.List<Integer> $lval1, int $expr1, int $expr2) {
        $lval1.set(Math.abs($expr1) % $lval1.size(), Integer.valueOf($expr2));
    }

    public static void $mut2(java.util.List<Long> $lval1, int $expr1, long $expr2) {
        $lval1.set(Math.abs($expr1) % $lval1.size(), Long.valueOf($expr2));
    }

    public static void $mut3(java.util.List<Double> $lval1, int $expr1, double $expr2) {
        $lval1.set(Math.abs($expr1) % $lval1.size(), Double.valueOf($expr2));
    }

    public static void $mut4(java.util.List<Float> $lval1, int $expr1, float $expr2) {
        $lval1.set(Math.abs($expr1) % $lval1.size(), Float.valueOf($expr2));
    }

    public static void $mut5(java.util.List<Short> $lval1, int $expr1, short $expr2) {
        $lval1.set(Math.abs($expr1) % $lval1.size(), Short.valueOf($expr2));
    }

    public static void $mut6(java.util.List<Byte> $lval1, int $expr1, byte $expr2) {
        $lval1.set(Math.abs($expr1) % $lval1.size(), Byte.valueOf($expr2));
    }

    public static void $mut7(java.util.List<String> $lval1, int $expr1, String $expr2) {
        $lval1.set(Math.abs($expr1) % $lval1.size(), $expr2);
    }

    public static void $mut8(java.util.List<Object> $lval1, int $expr1, Object $expr2) {
        $lval1.set(Math.abs($expr1) % $lval1.size(), $expr2);
    }

}
