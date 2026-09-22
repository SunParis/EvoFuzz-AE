class EscapeAnalysis {
    public static int $priority = 50;
    public static void $mut1(int $lval1, int $lval2, int $lval3, int $lval4, $cls1 $lval5) {
        for($lval4 = 0; $lval4 < 10_000; $lval4++) {
            $lval5 = new $cls1();
            $lval5.x = $lval1;
            $lval5.y = $lval2;
            $lval3 = $lval5.x + $lval5.y;
            $stmt();
        }
    }

    public static $cls $new1() {
        return new $cls();
    }

    public static void $check1($cls $lval1) {
        System.out.println("$lval1.x: " + $lval1.x + ", $lval1.y: " + $lval1.y);
    }

}
class $cls1{
    int x, y;
}