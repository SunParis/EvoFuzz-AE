import java.math.BigDecimal;

public class TplClass7114 {

    private static final void method(java.math.BigDecimal quotient, int failures, java.math.BigDecimal[] testCase) throws Throwable {
        if (!(quotient = testCase[0].divideToIntegralValue(testCase[1])).equals(testCase[2])) {
            failures++;
            // System.err.println("exact     = " + exact       + " scale = " + exact.scale());
        }
    }
}

