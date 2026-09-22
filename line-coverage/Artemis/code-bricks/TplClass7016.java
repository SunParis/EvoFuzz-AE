import java.math.BigDecimal;

public class TplClass7016 {

    private static final void method(java.lang.String[][] testCases, int errors) throws Throwable {
        for (String[] testCase : testCases) {
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
}

