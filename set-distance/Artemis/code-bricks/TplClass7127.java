import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class TplClass7127 {

    private static final void method(int i, java.math.BigDecimal dividend, java.math.BigDecimal divisor, int failures, java.math.BigDecimal[] quotients) throws Throwable {
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

