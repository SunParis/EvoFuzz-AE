import java.math.BigDecimal;

public class TplClass7070 {

    private static final void method(java.math.BigDecimal zero, int failures, int[] scales) throws Throwable {
        for (int scale : scales) {
            try {
                BigDecimal bd = zero.setScale(scale);
            } catch (ArithmeticException e) {
                failures++;
            }
        }
    }
}

