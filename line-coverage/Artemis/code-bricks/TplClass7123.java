import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class TplClass7123 {

    private static final void method(java.math.BigDecimal quotient, java.math.BigDecimal result, java.math.BigDecimal divisor, int failures, java.math.BigDecimal dividend, int i) throws Throwable {
        try {
            result = dividend.divideToIntegralValue(divisor, new MathContext(i, RoundingMode.DOWN));
        } catch (ArithmeticException e) {
            if (quotient != null) {
                failures++;
            }
        }
    }
}

