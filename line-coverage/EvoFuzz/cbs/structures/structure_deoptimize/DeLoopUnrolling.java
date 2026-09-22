public class DeLoopUnrolling {
    public static int $priority = 50;
    public static int $gbvar1;
    public static boolean $gbvar2;
    public static void $mut1(int $lval1, int $expr1, int $expr2, int $lval2, int $lval3) {
        $gbvar2 = false;
        for($lval1 = 0; $lval1 < ($expr1 + $expr2 + $gbvar1); $lval1++) { 
            for($lval2 = 0; $lval2 < 4; $lval2++) {
                if($gbvar2) {
                    $lval3 += $lval1 * $lval2;
                }
            }     
            $lval3 += $lval1;
            if ($lval1 != $expr1 + $expr2 + $gbvar1 - 1) {
                $gbvar2 = true;   
            }
            $stmt();
        }
    }
}
