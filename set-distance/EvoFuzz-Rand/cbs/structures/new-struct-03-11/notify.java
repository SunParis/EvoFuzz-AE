
public class notify {
    
    public static void $mut1(Object $lval1) {
        try {
            $lval1.wait(1);
        } catch (InterruptedException $tmp1) {
            $stmt();
        }
    }

    public static void $mut2(Object $lval1) {
        
        synchronized ($lval1) {
            try {
                $lval1.wait(1);
            } catch (InterruptedException $tmp1) {
                $stmt();
            }
        }
    }

    public static void $mut3(Object $lval1) {
        
        synchronized ($lval1) {
            try {
                $lval1.wait(1);
            } catch (InterruptedException $tmp1) {
                $stmt();
            }
            $stmt();
        }
    }

    public static void $mut4(Object $lval1) {
        
        synchronized ($lval1) {
            try {
                $lval1.wait(1);
            } catch (InterruptedException $tmp1) {
            }
            $stmt();
        }
    }

    public static void $mut5(Object $lval1) {
        
        synchronized ($lval1) {
            try {
                $lval1.wait(1);
            } catch (InterruptedException $tmp1) {
            }
        }
    }
}
