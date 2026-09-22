import java.math.BigDecimal;

public class TplClass7125 {

    private static final void method(int failures, java.math.BigDecimal[] testCase) throws Throwable {
        BigDecimal quotient;
        if (!(quotient = testCase[0].divideToIntegralValue(testCase[1])).equals(testCase[2])) {
            failures++;
            // System.err.println("exact     = " + exact       + " scale = " + exact.scale());
        }
    }
}

