import java.math.MathContext;
import java.math.BigDecimal;

public class TplClass7091 {

    private static final void method(java.math.BigDecimal result, java.math.BigDecimal zero, int failures, java.math.BigDecimal expected, java.math.BigDecimal one, java.math.MathContext longEnough) throws Throwable {
        if (!(result = zero.divide(one, longEnough)).equals(expected)) {
            failures++;
        }
    }
}

