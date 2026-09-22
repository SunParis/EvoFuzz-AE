import java.math.MathContext;
import java.math.BigDecimal;

public class TplClass7088 {

    private static final void method(java.math.BigDecimal result, int failures, java.math.BigDecimal expected, java.math.MathContext longEnough, java.math.BigDecimal value, java.math.BigDecimal zero1) throws Throwable {
        if (!(result = zero1.multiply(value, longEnough)).equals(expected)) {
            failures++;
        }
    }
}

