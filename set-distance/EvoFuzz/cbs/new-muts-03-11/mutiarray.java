

public class mutiarray {
    public static int $priority = 50;
    
    public static void $mut1(int $lval1, Object $lval2) {
        $lval2 = multiarr4($lval1, $lval1, $lval1, $lval1);
    }

    public static void $mut2(int $lval1, Object $lval2) {
        $lval2 = multiarr5($lval1, $lval1, $lval1, $lval1, $lval1);
    }

    public static void $mut3(int $lval1, Object $lval2) {
        $lval2 = multiarr6($lval1, $lval1, $lval1, $lval1, $lval1, $lval1);
    }

    public static Object multiarr4(int a, int b, int c, int d) {
        return new int[a][b][c][d];
    }

    public static Object multiarr5(int a, int b, int c, int d, int e) {
        return new int[a][b][c][d][e];
    }
    
    public static Object multiarr6(int a, int b, int c, int d, int e, int f) {
        return new int[a][b][c][d][e][f];
    }
}
