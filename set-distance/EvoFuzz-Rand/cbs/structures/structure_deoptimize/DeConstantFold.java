class DeConstantFold {
    public static int $priority = 50;
    public static int $gbvar1;
    public static double $gbvar2;
    public static long $gbvar3;
    public static float $gbvar4;
    public static String $gbvar5;
    
    public static void $mut1(int $lval1, int $lval2, int $lval3,int $lval4) {
        for($lval4 = 0; $lval4 < 333; $lval4++){
            $lval3 += constantFold_deoptimize_int($lval1,$lval2);
            $stmt();
        }
        $gbvar1 -= 45;
        $lval3 += constantFold_deoptimize_int($lval1,$lval2);
    }
    public static int constantFold_deoptimize_int(int constantFold_deoptimize_a, int constantFold_deoptimize_b) {
        constantFold_deoptimize_a = 34;
        constantFold_deoptimize_b = 32878;
        return constantFold_deoptimize_b * $gbvar1 + constantFold_deoptimize_b;
    }
    public static void $mut2(double $lval1, double $lval2, double $lval3,int $lval4) {
        for($lval4 = 0; $lval4 < 333; $lval4++){ 
            $lval3 += constantFold_deoptimize_double($lval1,$lval2);
            $stmt();
        }
        $gbvar2 -= 45.5d;
        $lval3 += constantFold_deoptimize_double($lval1,$lval2);
    }

    public static double constantFold_deoptimize_double(double constantFold_deoptimize_a, double constantFold_deoptimize_b) {
        constantFold_deoptimize_a = 34.5;
        constantFold_deoptimize_b = 32878.5;
        return constantFold_deoptimize_b * $gbvar2 + constantFold_deoptimize_b;
    }

    public static void $mut3(long $lval1, long $lval2, long $lval3,int $lval4) {
        for($lval4 = 0; $lval4 < 333; $lval4++){ 
            $lval3 += constantFold_deoptimize_long($lval1,$lval2);
            $stmt();
        }
        $gbvar3 -= 45L;
        $lval3 += constantFold_deoptimize_long($lval1,$lval2);
    }
    public static long constantFold_deoptimize_long(long constantFold_deoptimize_a, long constantFold_deoptimize_b) {
        constantFold_deoptimize_a = 34L;
        constantFold_deoptimize_b = 32878L;
        return constantFold_deoptimize_b * $gbvar3 + constantFold_deoptimize_b;
    }

    public static void $mut4(float $lval1, float $lval2, float $lval3,int $lval4) {
        for($lval4 = 0; $lval4 < 333; $lval4++){ 
            $lval3 += constantFold_deoptimize_float($lval1,$lval2);
            $stmt();
        }
        $gbvar4 -= 45f;
        $lval3 += constantFold_deoptimize_float($lval1,$lval2);
    }
    public static float constantFold_deoptimize_float(float constantFold_deoptimize_a, float constantFold_deoptimize_b) {
        constantFold_deoptimize_a = 34.5f;
        constantFold_deoptimize_b = 32878.5f;
        return constantFold_deoptimize_b * $gbvar4 + constantFold_deoptimize_b;
    }
    
    public static void $mut7(String $lval1, String $lval2, String $lval3,int $lval4) {
        for($lval4 = 0; $lval4 < 333; $lval4++){ 
            $lval3 += constantFold_deoptimize_string($lval1,$lval2);
            $stmt();
        }
        $gbvar5 = "Hello World";
        $lval3 += constantFold_deoptimize_string($lval1,$lval2);
    }
    public static String constantFold_deoptimize_string(String constantFold_deoptimize_a, String constantFold_deoptimize_b) {
        constantFold_deoptimize_a = "Hello";
        constantFold_deoptimize_b = "World";
        return constantFold_deoptimize_b + $gbvar5 + constantFold_deoptimize_b;
    }
}