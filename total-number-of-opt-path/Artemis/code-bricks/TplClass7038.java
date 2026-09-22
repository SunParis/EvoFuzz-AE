import java.math.BigDecimal;

public class TplClass7038 {

    private static final void method(java.math.BigDecimal[][] testCases, int i) throws Throwable {
        if (!(testCases[i][0]).stripTrailingZeros().equals(testCases[i][1])) {
        }
        testCases[i][0] = testCases[i][0].negate();
        testCases[i][1] = testCases[i][1].negate();
        if (!(testCases[i][0]).stripTrailingZeros().equals(testCases[i][1])) {
        }
    }
}

