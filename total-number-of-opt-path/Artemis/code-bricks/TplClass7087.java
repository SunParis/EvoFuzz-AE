import java.math.MathContext;
import java.math.BigDecimal;

public class TplClass7087 {

    private static final void method(java.math.BigDecimal result, int failures, java.math.BigDecimal value, java.math.BigDecimal expected, java.math.BigDecimal zero1) throws Throwable {
        if (!(result = zero1.multiply(value, MathContext.UNLIMITED)).equals(expected)) {
            failures++;
        }
    }
}

