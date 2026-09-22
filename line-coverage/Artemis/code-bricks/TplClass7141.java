import java.math.BigDecimal;

public class TplClass7141 {

    private static final void method(int failures, java.lang.String bigValue) throws Throwable {
        BigDecimal bd = new BigDecimal(bigValue);
        long longValueExact = bd.longValueExact();
        failures++;
    }
}

