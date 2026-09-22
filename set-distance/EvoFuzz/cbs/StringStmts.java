public class StringStmts {
    
    public static int $priority = 20;

    public static void $mut0(String $lval1, String $expr1) {
        $lval1 += $expr1;
    }

    public static void $mut1(String $lval1, String $lval2) {
        $lval1 += $lval2;
    }

    public static void $mut2(String $lval1, String $expr1, String $const1) {
        $lval1 = $const1;
        $lval1 += $expr1;
    }

    public static void $mut3(String $lval1, String $lval2, String $expr1) {
        $lval1 = $expr1 + $lval2;
    }

    public static void $mut4(String $lval1, String $expr1, String $const1) {
        $lval1 = $const1 + $expr1;
    }

    public static void $mut5(String $lval1) {
        $lval1 = "";
    }

}
