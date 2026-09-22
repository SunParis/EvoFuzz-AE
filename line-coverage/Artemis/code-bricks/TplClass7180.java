import java.math.BigDecimal;

public class TplClass7180 {

    private static final void method(java.math.BigDecimal x, int failures) throws Throwable {
        // 
        x = new BigDecimal("1e2147483647").add(new BigDecimal(1));
        failures++;
    }
}

