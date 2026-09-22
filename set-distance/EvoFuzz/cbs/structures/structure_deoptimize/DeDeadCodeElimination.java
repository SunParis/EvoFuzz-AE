class DeDeadCodeElimination {
    
    public static int $priority = 50;
    
    public static boolean $gbvar1;
    
    public static int $gbvar2;
    
    public static void $mut1(boolean $lval1, int $lval2, int $expr1,int $lval3) {
        $lval2 += $expr1;
        $gbvar1 = $lval1 && !$lval1;
        for ($lval3 = 0; $lval3 < $expr1; $lval3++) {
            $lval2 += deadCodeElimination_deoptimize($lval3);
            $stmt();
        }
        $gbvar1 = true;
        $lval2 += deadCodeElimination_deoptimize($lval2);
    }
    
    public static int deadCodeElimination_deoptimize(int deadCodeElimination_deoptimize_a) {
        if($gbvar1){
            return deadCodeElimination_deoptimize_a * $gbvar2;
        }
        return 0;
    }
}
