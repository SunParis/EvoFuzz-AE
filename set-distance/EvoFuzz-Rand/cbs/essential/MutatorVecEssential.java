import java.util.Vector;

public class MutatorVecEssential {

    public static java.util.Vector<Long> $new1() {
        return new java.util.Vector<Long>(AllFuzzerDefs.ARRAY_SIZE);
    }

    public static java.util.Vector<Integer> $new2() {
        return new java.util.Vector<Integer>(AllFuzzerDefs.ARRAY_SIZE);
    }

    public static java.util.Vector<Double> $new3() {
        return new java.util.Vector<Double>(AllFuzzerDefs.ARRAY_SIZE);
    }

    public static java.util.Vector<Float> $new4() {
        return new java.util.Vector<Float>(AllFuzzerDefs.ARRAY_SIZE);
    }

    public static java.util.Vector<Short> $new5() {
        return new java.util.Vector<Short>(AllFuzzerDefs.ARRAY_SIZE);
    }

    public static java.util.Vector<Byte> $new6() {
        return new java.util.Vector<Byte>(AllFuzzerDefs.ARRAY_SIZE);
    }

    public static java.util.Vector<String> $new7() {
        return new java.util.Vector<String>(AllFuzzerDefs.ARRAY_SIZE);
    }

    public static java.util.Vector<Object> $new8() {
        return new java.util.Vector<Object>(AllFuzzerDefs.ARRAY_SIZE);
    }

    public static void $init1(java.util.Vector<Long> $lval1) {
        for (int $tmp1 = 0; $tmp1 < AllFuzzerDefs.ARRAY_SIZE; $tmp1++) {
            $lval1.add(Long.valueOf(1L + $tmp1));
        }
    }

    public static void $init2(java.util.Vector<Integer> $lval1) {
        for (int $tmp1 = 0; $tmp1 < AllFuzzerDefs.ARRAY_SIZE; $tmp1++) {
            $lval1.add(Integer.valueOf(1 + $tmp1));
        }
    }

    public static void $init3(java.util.Vector<Double> $lval1) {
        for (int $tmp1 = 0; $tmp1 < AllFuzzerDefs.ARRAY_SIZE; $tmp1++) {
            $lval1.add(Double.valueOf(1.0 + $tmp1));
        }
    }

    public static void $init4(java.util.Vector<Float> $lval1) {
        for (int $tmp1 = 0; $tmp1 < AllFuzzerDefs.ARRAY_SIZE; $tmp1++) {
            $lval1.add(Float.valueOf(1.0f + $tmp1));
        }
    }

    public static void $init5(java.util.Vector<Short> $lval1) {
        for (int $tmp1 = 0; $tmp1 < AllFuzzerDefs.ARRAY_SIZE; $tmp1++) {
            $lval1.add(Short.valueOf((short)(1 + $tmp1)));
        }
    }

    public static void $init6(java.util.Vector<Byte> $lval1) {
        for (int $tmp1 = 0; $tmp1 < AllFuzzerDefs.ARRAY_SIZE; $tmp1++) {
            $lval1.add(Byte.valueOf((byte)(1 + $tmp1)));
        }
    }

    public static void $init7(java.util.Vector<String> $lval1) {
        for (int $tmp1 = 0; $tmp1 < AllFuzzerDefs.ARRAY_SIZE; $tmp1++) {
            $lval1.add(String.valueOf(String.valueOf($tmp1)));
        }
    }

    public static void $init8(java.util.Vector<Object> $lval1) {
        for (int $tmp1 = 0; $tmp1 < AllFuzzerDefs.ARRAY_SIZE; $tmp1++) {
            $lval1.add(new Object());
        }
    }

    public static void $check1(java.util.Vector<Long> $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.size(); $tmp1 = (2 + $tmp1 * 3) / 2) {
            System.out.println("java.util.Vector<Long> $lval1:: at " + $tmp1 + " `" + String.valueOf($lval1.get($tmp1)) + "`");
        }
    }

    public static void $check2(java.util.Vector<Integer> $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.size(); $tmp1 = (2 + $tmp1 * 3) / 2) {
            System.out.println("java.util.Vector<Integer> $lval1:: at " + $tmp1 + " `" + String.valueOf($lval1.get($tmp1)) + "`");
        }
    }

    public static void $check3(java.util.Vector<Double> $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.size(); $tmp1 = (2 + $tmp1 * 3) / 2) {
            System.out.println("java.util.Vector<Double> $lval1:: at " + $tmp1 + " `" + String.valueOf($lval1.get($tmp1)) + "`");
        }
    }

    public static void $check4(java.util.Vector<Float> $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.size(); $tmp1 = (2 + $tmp1 * 3) / 2) {
            System.out.println("java.util.Vector<Float>$lval1:: at " + $tmp1 + " `" + String.valueOf($lval1.get($tmp1)) + "`");
        }
    }

    public static void $check5(java.util.Vector<Short> $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.size(); $tmp1 = (2 + $tmp1 * 3) / 2) {
            System.out.println("java.util.Vector<Short> $lval1:: at " + $tmp1 + " `" + String.valueOf($lval1.get($tmp1)) + "`");
        }
    }

    public static void $check6(java.util.Vector<Byte> $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.size(); $tmp1 = (2 + $tmp1 * 3) / 2) {
            System.out.println("java.util.Vector<Byte> $lval1:: at " + $tmp1 + " `" + String.valueOf($lval1.get($tmp1)) + "`");
        }
    }

    public static void $check7(java.util.Vector<String> $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.size(); $tmp1 = (2 + $tmp1 * 3) / 2) {
            System.out.println("java.util.Vector<String $lval1:: at " + $tmp1 + " `" + String.valueOf($lval1.get($tmp1)) + "`");
        }
    }

    public static void $check8(java.util.Vector<Object> $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.size(); $tmp1 = (2 + $tmp1 * 3) / 2) {
            System.out.println("java.util.Vector<Object> $lval1:: at " + $tmp1 + " `" + $lval1.get($tmp1).getClass().getName() + "`");
        }
    }
}
