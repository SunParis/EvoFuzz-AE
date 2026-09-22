import java.math.BigDecimal;

public class TplClass7116 {

    private static final void method(java.math.BigDecimal result, java.math.BigDecimal quotient, java.math.BigDecimal divisor, int failures, int i, java.math.BigDecimal dividend) throws Throwable {
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

