import java.math.BigDecimal;

public class TplClass7138 {

    private static final void method(java.lang.String longValue, int failures) throws Throwable {
        try {
            BigDecimal bd = new BigDecimal(longValue);
            long longValueExact = bd.longValueExact();
        } catch (Exception e) {
            failures++;
        }
    }
}

