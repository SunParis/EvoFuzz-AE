import java.util.List;
import java.util.ArrayList;


public class MutatorListEssential {
    
    public static java.util.List<Long> $new1() {
        return new java.util.ArrayList<Long>(AllFuzzerDefs.ARRAY_SIZE);
    }

    public static java.util.List<Integer> $new2() {
        return new java.util.ArrayList<Integer>(AllFuzzerDefs.ARRAY_SIZE);
    }

    public static java.util.List<Double> $new3() {
        return new java.util.ArrayList<Double>(AllFuzzerDefs.ARRAY_SIZE);
    }

    public static java.util.List<Float> $new4() {
        return new java.util.ArrayList<Float>(AllFuzzerDefs.ARRAY_SIZE);
    }

    public static java.util.List<Short> $new5() {
        return new java.util.ArrayList<Short>(AllFuzzerDefs.ARRAY_SIZE);
    }

    public static java.util.List<Byte> $new6() {
        return new java.util.ArrayList<Byte>(AllFuzzerDefs.ARRAY_SIZE);
    }

    public static java.util.List<String> $new7() {
        return new java.util.ArrayList<String>(AllFuzzerDefs.ARRAY_SIZE);
    }

    public static java.util.List<Object> $new8() {
        return new java.util.ArrayList<Object>(AllFuzzerDefs.ARRAY_SIZE);
    }

    public static void $init1(java.util.List<Long> $lval1) {
        for (int $tmp1 = 0; $tmp1 < AllFuzzerDefs.ARRAY_SIZE; $tmp1++) {
            $lval1.add(Long.valueOf(1L + $tmp1));
        }
    }

    public static void $init2(java.util.List<Integer> $lval1) {
        for (int $tmp1 = 0; $tmp1 < AllFuzzerDefs.ARRAY_SIZE; $tmp1++) {
            $lval1.add(Integer.valueOf(1 + $tmp1));
        }
    }

    public static void $init3(java.util.List<Double> $lval1) {
        for (int $tmp1 = 0; $tmp1 < AllFuzzerDefs.ARRAY_SIZE; $tmp1++) {
            $lval1.add(Double.valueOf(1.0 + $tmp1));
        }
    }

    public static void $init4(java.util.List<Float> $lval1) {
        for (int $tmp1 = 0; $tmp1 < AllFuzzerDefs.ARRAY_SIZE; $tmp1++) {
            $lval1.add(Float.valueOf(1.0f + $tmp1));
        }
    }

    public static void $init5(java.util.List<Short> $lval1) {
        for (int $tmp1 = 0; $tmp1 < AllFuzzerDefs.ARRAY_SIZE; $tmp1++) {
            $lval1.add(Short.valueOf((short)(1 + $tmp1)));
        }
    }

    public static void $init6(java.util.List<Byte> $lval1) {
        for (int $tmp1 = 0; $tmp1 < AllFuzzerDefs.ARRAY_SIZE; $tmp1++) {
            $lval1.add(Byte.valueOf((byte)(1 + $tmp1)));
        }
    }

    public static void $init7(java.util.List<String> $lval1) {
        for (int $tmp1 = 0; $tmp1 < AllFuzzerDefs.ARRAY_SIZE; $tmp1++) {
            $lval1.add(String.valueOf(String.valueOf($tmp1)));
        }
    }

    public static void $init8(java.util.List<Object> $lval1) {
        for (int $tmp1 = 0; $tmp1 < AllFuzzerDefs.ARRAY_SIZE; $tmp1++) {
            $lval1.add(new Object());
        }
    }

    public static void $check1(java.util.List<Long> $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.size(); $tmp1 = (2 + $tmp1 * 3) / 2) {
            System.out.println("java.util.List<Long> $lval1:: at " + $tmp1 + " `" + String.valueOf($lval1.get($tmp1)) + "`");
        }
    }

    public static void $check2(java.util.List<Integer> $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.size(); $tmp1 = (2 + $tmp1 * 3) / 2) {
            System.out.println("java.util.List<Integer> $lval1:: at " + $tmp1 + " `" + String.valueOf($lval1.get($tmp1)) + "`");
        }
    }

    public static void $check3(java.util.List<Double> $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.size(); $tmp1 = (2 + $tmp1 * 3) / 2) {
            System.out.println("java.util.List<Double> $lval1:: at " + $tmp1 + " `" + String.valueOf($lval1.get($tmp1)) + "`");
        }
    }

    public static void $check4(java.util.List<Float> $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.size(); $tmp1 = (2 + $tmp1 * 3) / 2) {
            System.out.println("java.util.List<Float>$lval1:: at " + $tmp1 + " `" + String.valueOf($lval1.get($tmp1)) + "`");
        }
    }

    public static void $check5(java.util.List<Short> $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.size(); $tmp1 = (2 + $tmp1 * 3) / 2) {
            System.out.println("java.util.List<Short> $lval1:: at " + $tmp1 + " `" + String.valueOf($lval1.get($tmp1)) + "`");
        }
    }

    public static void $check6(java.util.List<Byte> $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.size(); $tmp1 = (2 + $tmp1 * 3) / 2) {
            System.out.println("java.util.List<Byte> $lval1:: at " + $tmp1 + " `" + String.valueOf($lval1.get($tmp1)) + "`");
        }
    }

    public static void $check7(java.util.List<String> $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.size(); $tmp1 = (2 + $tmp1 * 3) / 2) {
            System.out.println("java.util.List<String $lval1:: at " + $tmp1 + " `" + String.valueOf($lval1.get($tmp1)) + "`");
        }
    }

    public static void $check8(java.util.List<Object> $lval1) {
        for (int $tmp1 = 0; $tmp1 < $lval1.size(); $tmp1 = (2 + $tmp1 * 3) / 2) {
            System.out.println("java.util.List<Object> $lval1:: at " + $tmp1 + " `" + $lval1.get($tmp1).getClass().getName() + "`");
        }
    }

}
