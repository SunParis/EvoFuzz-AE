import java.math.BigDecimal;

public class TplClass7094 {

    private static final void method(java.math.BigDecimal zero, int scale, int failures) throws Throwable {
        try {
            BigDecimal bd = zero.setScale(scale);
        } catch (ArithmeticException e) {
            failures++;
        }
    }
}

