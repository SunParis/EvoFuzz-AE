import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class TplClass7128 {

    private static final void method(java.math.BigDecimal result, java.math.BigDecimal dividend, int i, java.math.BigDecimal divisor) throws Throwable {
        result = dividend.divideToIntegralValue(divisor, new MathContext(i, RoundingMode.DOWN));
    }
}

