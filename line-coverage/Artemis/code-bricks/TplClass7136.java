import java.math.BigDecimal;

public class TplClass7136 {

    private static final void method(java.lang.String[] testStrings, int failures) throws Throwable {
        for (String longValue : testStrings) {
            try {
                BigDecimal bd = new BigDecimal(longValue);
                long longValueExact = bd.longValueExact();
            } catch (Exception e) {
                failures++;
            }
        }
    }
}

