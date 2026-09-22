import java.math.BigDecimal;
import java.math.MathContext;

public class TplClass7124 {

    private static final void method(java.math.BigDecimal result, java.math.BigDecimal divisor, int failures, java.math.MathContext mc, java.math.BigDecimal expected, java.math.BigDecimal dividend) throws Throwable {
        try {
            result = dividend.divideToIntegralValue(divisor, mc);
        } catch (ArithmeticException e) {
            if (expected != null) {
                failures++;
            }
        }
    }
}

