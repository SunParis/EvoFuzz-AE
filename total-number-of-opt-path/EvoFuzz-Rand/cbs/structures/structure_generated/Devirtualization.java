class Devirtualization {
    public static int $priority = 50;
    public static void $mut1(int $lval1, int $lval2, int $lval3, $cls $lval4) {
        for ($lval1 = 0; $lval1 < 333; $lval1++) {
            $lval4 = new $clsImpl();
            $lval3 += $lval4.foo();
            $stmt();
        }
    }

    public static $clsImpl $new1() {
        return new $clsImpl();
    }

    public static $cls $new2() {
        return new $clsImpl();
    }

    public static void $check1($cls $lval1) {
        System.out.println("$lval1: " + $lval1.foo());
    }

    public static void $check2($clsImpl $lval1) {
        System.out.println("$lval1: " + $lval1.foo());
    }

}

interface $cls{
    int foo();
}
class $clsImpl implements $cls {
   public int foo() { return 42; }
}