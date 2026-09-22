import java.math.BigDecimal;

public class TplClass7018 {

    private static final void method(java.math.BigDecimal bd, java.lang.String s, int errors, java.lang.String[] testCase) throws Throwable {
        if (bd.signum() != 0 && !(s = (bd.toPlainString())).equals("-" + testCase[1])) {
            errors++;
        }
    }
}

