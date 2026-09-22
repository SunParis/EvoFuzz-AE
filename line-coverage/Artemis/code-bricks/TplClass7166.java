import java.math.BigDecimal;

public class TplClass7166 {

    private static final void method(java.math.BigDecimal x, int failures, java.math.BigDecimal xPower) throws Throwable {
        try {
            for (int i = 0; i < 100; i++) {
                xPower = xPower.multiply(x);
            }
        } catch (Exception ex) {
            failures++;
        }
    }
}

