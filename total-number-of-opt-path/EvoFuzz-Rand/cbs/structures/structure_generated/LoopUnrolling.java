public class LoopUnrolling {
    public static int $priority = 50;
    public static int $gbvar;
    public static void $mut1(int $lval1, int $expr1, int $expr2, int $lval2) {
        for($lval1 = 0; $lval1 < ($expr1 + $expr2 + $gbvar); $lval1++) {
            $lval2 = $lval2 + $lval1;
            $stmt();
        }
    }
}
