class LockElimination {
    public static int $priority = 50;

    public static void $mut1(Object $lval1) {
        $lval1 = new Object();
        synchronized ($lval1) {
            $stmt();
        }
    }
    
    public static void $mut2(Object $lval1, Object $lval2) {
        $lval1 = new Object();
        $lval2 = new Object();
    
        synchronized ($lval1) {
            $stmt();
        }
        synchronized ($lval2) {
            $stmt();
        }
    }
    
    public static void $mut3(Object $lval1, Object $lval2) {
        $lval1 = new Object();
        $lval2 = new Object();
        synchronized ($lval1) {
            $stmt();
            synchronized ($lval2) {
                $stmt();
            } 
        }
    }

    public static void $mut4(Object $lval1) {
        $lval1 = new Object();
        synchronized ($lval1) {
            $stmt();
        }
        synchronized ($lval1) {
            $stmt();
        }
    }
    
    public static void $mut5(Object $lval1, Object $lval2, Object $lval3, int $lval4) {
        if ($lval4 % 2 == 0) {
            $lval3 = $lval1;
        } else {
            $lval3 = $lval2;
        }
        synchronized ($lval3) {
            $stmt();
        }
    }
}