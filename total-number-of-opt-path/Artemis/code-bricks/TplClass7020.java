import java.math.BigDecimal;

public class TplClass7020 {

    private static final void method(int errors, java.lang.String[] testCase) throws Throwable {
        BigDecimal bd = new BigDecimal(testCase[0]);
        String s;
        if (!(s = bd.toPlainString()).equals(testCase[1])) {
            errors++;
        }
        bd = new BigDecimal("-" + testCase[0]);
        if (bd.signum() != 0 && !(s = (bd.toPlainString())).equals("-" + testCase[1])) {
            errors++;
        }
    }
}

