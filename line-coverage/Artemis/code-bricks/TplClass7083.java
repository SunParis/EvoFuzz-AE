import java.math.MathContext;
import java.math.BigDecimal;

public class TplClass7083 {

    private static final void method(java.math.BigDecimal result, int failures, java.math.BigDecimal zero2, java.math.BigDecimal expected, java.math.BigDecimal zero1) throws Throwable {
        if (!(result = zero1.subtract(zero2, MathContext.UNLIMITED)).equals(expected)) {
            failures++;
        }
    }
}

