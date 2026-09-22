import java.math.BigDecimal;

public class TplClass7092 {

    private static final void method(java.lang.String result, java.math.BigDecimal bd, int failures, java.lang.String[] testCase) throws Throwable {
        if (!result.equals(testCase[1]) || !bd.equals(new BigDecimal(result))) {
            failures++;
        }
    }
}

