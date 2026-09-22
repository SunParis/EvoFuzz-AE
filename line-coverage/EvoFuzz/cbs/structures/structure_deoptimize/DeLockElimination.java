class DeLockElimination {
    public static int $priority = 50;
    volatile static Object $gbvar1;
    volatile static Object $gbvar3;
    public static boolean $gbvar2;
    
    public static void $mut1(int $lval1,int $lval2) {
        $gbvar2 = false;
        for ($lval1 = 0; $lval1 < 333; $lval1++) {
            lockElimination_deoptimize_1($lval2);
            $stmt();
        }
        $gbvar2 = false;
        lockElimination_deoptimize_1($lval2);
    }
    
    public static void lockElimination_deoptimize_1(int lockElimination_deoptimize_1_a) {
        Object $tmp1 = new Object();
        synchronized ($tmp1) {
            lockElimination_deoptimize_1_a++;
        }
        if ($gbvar2) {
            $gbvar1 = $tmp1;
        }
    }
    
    public static void $mut2(int $lval1,int $lval2) {
        $gbvar2 = false;
        for ($lval1 = 0; $lval1 < 333; $lval1++) {
            lockElimination_deoptimize_2($lval2);
            $stmt();
        }
        $gbvar2 = true;
        lockElimination_deoptimize_2($lval2);
    }

    public static void lockElimination_deoptimize_2(int lockElimination_deoptimize_2_a) {
        Object $tmp1 = new Object();
        Object $tmp2 = new Object();
        synchronized ($tmp1) {
            lockElimination_deoptimize_2_a++;
        }
        synchronized ($tmp2) {
            lockElimination_deoptimize_2_a--;
            lockElimination_deoptimize_2_a--;
        }
        if ($gbvar2) {
            $gbvar1 = $tmp2;
            $gbvar3 = $tmp1;
        }
    }
    
    public static void $mut3(int $lval1,int $lval2) {
        $gbvar2 = false;
        for ($lval1 = 0; $lval1 < 333; $lval1++) {
            lockElimination_deoptimize_3($lval2);
            $stmt();
        }
        $gbvar2 = true;
        lockElimination_deoptimize_3($lval2);
    }
    
    public static void lockElimination_deoptimize_3(int lockElimination_deoptimize_3_a) {
        Object $tmp1 = new Object();
        Object $tmp2 = new Object();
        synchronized ($tmp1) {
            lockElimination_deoptimize_3_a++;
            synchronized ($tmp2) {
                lockElimination_deoptimize_3_a++;
                lockElimination_deoptimize_3_a -=10;
            } 
        }
        if ($gbvar2) {
            $gbvar1 = $tmp1;
            $gbvar3 = $tmp2;
        }
    }

    public static void $mut4(int $lval1,int $lval2) {
        $gbvar2 = false;
        for ($lval1 = 0; $lval1 < 333; $lval1++) {
            lockElimination_deoptimize_4($lval2);
            $stmt();
        }
        $gbvar2 = true;
        lockElimination_deoptimize_4($lval2);
    }

    public static void lockElimination_deoptimize_4(int lockElimination_deoptimize_2_a) {
        Object $tmp1 = new Object();
        synchronized ($tmp1) {
            lockElimination_deoptimize_2_a++;
        }
        synchronized ($tmp1) {
            lockElimination_deoptimize_2_a--;
            lockElimination_deoptimize_2_a--;
        }
        if ($gbvar2) {
            $gbvar1 = $tmp1;
            $gbvar3 = $tmp1;
        }
    }
}