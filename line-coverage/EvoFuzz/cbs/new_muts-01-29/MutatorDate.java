import java.util.Date;
import java.text.SimpleDateFormat;

public class MutatorDate {
    
    public static String $mut1(java.util.Date $lval1) {
        return $lval1.toString();
    }

    public static String $mut2(java.util.Date $lval1) {
        return (new java.text.SimpleDateFormat("yyyy-MM-dd-hh:mm:ss")).format($lval1);
    }

    public static java.util.Date $mut3(long $expr1) {
        return new java.util.Date(Math.abs($expr1) + 1L);
    }

    public static boolean $mut4(java.util.Date $lval1, java.util.Date $lval2) {
        return $lval1.equals($lval2);
    }

    public static boolean $mut5(java.util.Date $lval1, java.util.Date $lval2) {
        return $lval1.after($lval2);
    }

    public static boolean $mut6(java.util.Date $lval1, java.util.Date $lval2) {
        return $lval1.before($lval2);
    }

}
