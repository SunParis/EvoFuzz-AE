class DeDevirtualization {
    public static int $priority = 50;
    
    public static void $mut1(int $lval1, int $lval2, int $lval3, $cls $lval4, $cls $lval5) {
        $lval4 = new $clsImpl();
        $lval5 = new $clsImpl2();
        for ($lval1 = 0; $lval1 < 333; $lval1++) {
            $lval3 += $lval4.foo();
            $stmt();
        }
        $lval4 = $lval5;
        $lval3 += $lval4.foo();
    }

    public static $clsImpl $new1() {
        return new $clsImpl();
    }

    public static $clsImpl2 $new2() {
        return new $clsImpl2();
    }

    public static $cls $new3() {
        return new $clsImpl();
    }

    public static $cls $new4() {
        return new $clsImpl2();
    }

    public static void $check1($cls $lval1) {
        System.out.println("$lval1: " + $lval1.foo());
    }

    public static void $check2($clsImpl $lval1) {
        System.out.println("$lval1: " + $lval1.foo());
    }

    public static void $check3($clsImpl2 $lval1) {
        System.out.println("$lval1: " + $lval1.foo());
    }

}

interface $cls{
    int foo();
}
class $clsImpl implements $cls {
   public int foo() { return 42; }
}

class $clsImpl2 implements $cls {
   public int foo() { return 42; }
}