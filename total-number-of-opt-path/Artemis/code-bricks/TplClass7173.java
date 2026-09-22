import java.math.BigDecimal;

public class TplClass7173 {

    private static final void method(java.math.BigDecimal result, int failures, java.math.BigDecimal[] testCase, int exponent) throws Throwable {
        result = testCase[0].pow(exponent);
        if (!result.equals(testCase[2])) {
            failures++;
        }
    }
}

