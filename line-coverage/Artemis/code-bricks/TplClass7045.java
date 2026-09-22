import java.math.BigDecimal;

public class TplClass7045 {

    private static final void method(java.lang.String badString) throws Throwable {
        try {
            BigDecimal d = new BigDecimal(badString);
        } catch (NumberFormatException e) {
        }
    }
}

