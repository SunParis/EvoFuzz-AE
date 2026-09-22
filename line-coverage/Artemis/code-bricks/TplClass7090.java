import java.math.MathContext;
import java.math.BigDecimal;

public class TplClass7090 {

    private static final void method(java.math.BigDecimal result, java.math.BigDecimal zero, int failures, java.math.BigDecimal expected, java.math.BigDecimal one) throws Throwable {
        if (!(result = zero.divide(one, MathContext.UNLIMITED)).equals(expected)) {
            failures++;
        }
    }
}

