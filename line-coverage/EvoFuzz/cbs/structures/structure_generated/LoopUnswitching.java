public class LoopUnswitching{
    public static int $priority = 50;
    public static int $gbvar;
    public static void $mut1(int $lval1, int $expr1, int $expr2, int $lval2, int $expr3, int $lval4) { 
        for($lval1 = 0; $lval1 < ($expr1 + $expr2 + $gbvar); $lval1++) {
            if ($expr3 > 0) {
                $lval2 = $lval2 + $lval4;
                $stmt();
            }
            else {
                $lval2 = $lval2 - $lval4;
                $stmt();
            }
        }
    }
}
