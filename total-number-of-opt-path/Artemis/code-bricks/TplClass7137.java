import java.math.BigDecimal;

public class TplClass7137 {

    private static final void method(int failures, java.lang.String[] testStrings2) throws Throwable {
        for (String bigValue : testStrings2) {
            try {
                BigDecimal bd = new BigDecimal(bigValue);
                long longValueExact = bd.longValueExact();
                failures++;
            } catch (ArithmeticException e) {
                // Success;
            }
        }
    }
}

