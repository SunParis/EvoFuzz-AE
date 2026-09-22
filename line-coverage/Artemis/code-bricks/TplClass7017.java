import java.math.BigDecimal;

public class TplClass7017 {

    private static final void method(java.math.BigDecimal bd, java.lang.String s, int errors, java.lang.String[] testCase) throws Throwable {
        if (!(s = bd.toPlainString()).equals(testCase[1])) {
            errors++;
        }
    }
}

