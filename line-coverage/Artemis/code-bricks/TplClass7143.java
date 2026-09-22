import java.math.MathContext;
import java.math.BigDecimal;

public class TplClass7143 {

    private static final void method(java.math.BigDecimal bd2, int failures, java.math.MathContext mc, java.math.BigDecimal bd1) throws Throwable {
        // should overflow here
        bd2 = bd1.round(mc);
        failures++;
    }
}

