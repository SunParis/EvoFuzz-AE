import java.math.MathContext;
import java.math.BigDecimal;

public class TplClass7155 {

    private static final void method(java.math.BigDecimal bd, java.math.BigDecimal abs, int failures, java.math.BigDecimal expectedAbs, java.math.MathContext mc) throws Throwable {
        if (!abs.equals(expectedAbs)) {
            failures++;
        }
    }
}

