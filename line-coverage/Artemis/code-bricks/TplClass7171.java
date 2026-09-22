import java.math.BigDecimal;

public class TplClass7171 {

    private static final void method(java.math.BigDecimal result, int failures, java.math.BigDecimal[] testCase, int exponent) throws Throwable {
        try {
            result = testCase[0].pow(exponent);
            if (!result.equals(testCase[2])) {
                failures++;
            }
        } catch (ArithmeticException e) {
            if (testCase[2] != null) {
                failures++;
            }
        }
    }
}

