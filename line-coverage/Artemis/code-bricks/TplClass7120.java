import java.math.BigDecimal;
import java.math.MathContext;

public class TplClass7120 {

    private static final void method(java.math.BigDecimal result, java.math.BigDecimal divisor, int failures, java.math.MathContext mc, java.math.BigDecimal expected, java.math.BigDecimal dividend) throws Throwable {
        if (expected != null) {
            if (!result.equals(expected)) {
                failures++;
            }
        } else {
            if (result != null) {
                failures++;
            }
        }
    }
}

