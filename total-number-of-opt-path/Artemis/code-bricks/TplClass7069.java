import java.math.BigDecimal;

public class TplClass7069 {

    private static final void method(int failures, java.math.BigDecimal[] zeros, int[] scales) throws Throwable {
        for (BigDecimal zero : zeros) {
            for (int scale : scales) {
                try {
                    BigDecimal bd = zero.setScale(scale);
                } catch (ArithmeticException e) {
                    failures++;
                }
            }
        }
    }
}

