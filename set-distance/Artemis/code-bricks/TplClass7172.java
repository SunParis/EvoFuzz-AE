import java.math.BigDecimal;

public class TplClass7172 {

    private static final void method(int failures, java.math.BigDecimal[] testCase) throws Throwable {
        int exponent = testCase[1].intValueExact();
        BigDecimal result;
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

