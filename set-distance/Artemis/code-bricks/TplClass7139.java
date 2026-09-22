import java.math.BigDecimal;

public class TplClass7139 {

    private static final void method(int failures, java.lang.String bigValue) throws Throwable {
        try {
            BigDecimal bd = new BigDecimal(bigValue);
            long longValueExact = bd.longValueExact();
            failures++;
        } catch (ArithmeticException e) {
            // Success;
        }
    }
}

