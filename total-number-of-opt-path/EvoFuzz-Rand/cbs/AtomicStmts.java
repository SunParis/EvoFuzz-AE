import java.util.concurrent.atomic.AtomicReferenceArray;


public class AtomicStmts {
    
    public static int $mut1(AtomicReferenceArray<Integer> $lval1, int $lval2, int $expr1) {
        return (int) $lval1.getAndSet(Math.abs($expr1 % AllFuzzerDefs.ARRAY_SIZE), Integer.valueOf($lval2));
    }

    public static String $mut2(AtomicReferenceArray<String> $lval1, String $lval2, int $expr1) {
        return $lval1.getAndSet(Math.abs($expr1 % AllFuzzerDefs.ARRAY_SIZE), $lval2);
    }

    public static boolean $mut3(AtomicReferenceArray<Integer> $lval1, int $lval2, int $lval3, int $expr1) {
        return $lval1.compareAndSet(Math.abs($expr1 % AllFuzzerDefs.ARRAY_SIZE), Integer.valueOf($lval2), Integer.valueOf($lval3));
    }

    public static boolean $mut4(AtomicReferenceArray<String> $lval1, String $lval2, String $lval3, int $expr1) {
        return $lval1.compareAndSet(Math.abs($expr1 % AllFuzzerDefs.ARRAY_SIZE), $lval2, $lval3);
    }

    public static void $mut5(AtomicReferenceArray<Integer> $lval1, int $lval2, int $lval3, int $expr1) {
        $lval3 = (int) $lval1.getAndSet(Math.abs($expr1 % AllFuzzerDefs.ARRAY_SIZE), Integer.valueOf($lval2));
        return;
    }

    public static void $mut6(AtomicReferenceArray<String> $lval1, String $lval2, String $lval3, int $expr1) {
        $lval3 = $lval1.getAndSet(Math.abs($expr1 % AllFuzzerDefs.ARRAY_SIZE), $lval2);
        return;
    }

    public static void $mut7(AtomicReferenceArray<Integer> $lval1, int $lval2, int $lval3, boolean $lval4, int $expr1) {
        $lval4 = $lval1.compareAndSet(Math.abs($expr1 % AllFuzzerDefs.ARRAY_SIZE), Integer.valueOf($lval2), Integer.valueOf($lval3));
        return;
    }

    public static void $mut8(AtomicReferenceArray<String> $lval1, String $lval2, String $lval3, boolean $lval4, int $expr1) {
        $lval4 = $lval1.compareAndSet(Math.abs($expr1 % AllFuzzerDefs.ARRAY_SIZE), $lval2, $lval3);
        return;
    }

    public static AtomicReferenceArray<Integer> $new1() {
        return new AtomicReferenceArray<Integer>(AllFuzzerDefs.ARRAY_SIZE);
    }

    public static AtomicReferenceArray<String> $new2() {
        return new AtomicReferenceArray<String>(AllFuzzerDefs.ARRAY_SIZE);
    }

    public static void $init1(AtomicReferenceArray<Integer> $lval1) {
        for (int $tmp1 = 0; $tmp1 < AllFuzzerDefs.ARRAY_SIZE; $tmp1++) {
            $lval1.set($tmp1, Integer.valueOf($tmp1));
        }
    }

    public static void $init2(AtomicReferenceArray<String> $lval1) {
        for (int $tmp1 = 0; $tmp1 < AllFuzzerDefs.ARRAY_SIZE; $tmp1++) {
            $lval1.set($tmp1, "str" + $tmp1);
        }
    }

}
