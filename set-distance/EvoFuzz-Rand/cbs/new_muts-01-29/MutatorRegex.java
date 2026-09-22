import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MutatorRegex {
    
    public static void $mut1(Pattern $lval1, String $lval2, Matcher $lval3, String $expr1) {
        $lval3 = $lval1.matcher($expr1);
        $lval2 = $lval3.find() ? $lval3.group() : "";
    }

    public static void $mut2(Pattern $lval1, int $lval2, Matcher $lval3, String $expr1) {
        $lval3 = $lval1.matcher($expr1 + "abcDef");
        $lval2 = $lval3.end();
    }

}
