import java.math.BigDecimal;

public class TplClass7168 {

    private static final void method(java.math.BigDecimal[][] testCases, int failures) throws Throwable {
        for (BigDecimal[] testCase : testCases) {
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
}

