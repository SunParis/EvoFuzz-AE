import java.math.BigDecimal;

public class TplClass7113 {

    private static final void method(java.math.BigDecimal[][] moreTestCases, int failures) throws Throwable {
        for (BigDecimal[] testCase : moreTestCases) {
            BigDecimal quotient;
            if (!(quotient = testCase[0].divideToIntegralValue(testCase[1])).equals(testCase[2])) {
                failures++;
                // System.err.println("exact     = " + exact       + " scale = " + exact.scale());
            }
        }
    }
}

