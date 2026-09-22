import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class TplClass7112 {

    private static final void method(java.math.BigDecimal dividend, java.math.BigDecimal divisor, int failures, java.math.BigDecimal[] quotients) throws Throwable {
        for (int i = 0; i < quotients.length; i++) {
            BigDecimal result = null;
            BigDecimal quotient = quotients[i];
            try {
                result = dividend.divideToIntegralValue(divisor, new MathContext(i, RoundingMode.DOWN));
            } catch (ArithmeticException e) {
                if (quotient != null) {
                    failures++;
                }
            }
            if (quotient != null) {
                if (!result.equals(quotient)) {
                    failures++;
                }
            } else {
                if (result != null) {
                    failures++;
                }
            }
        }
    }
}

