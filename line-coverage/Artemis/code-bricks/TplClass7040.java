import java.math.BigDecimal;

public class TplClass7040 {

    private static final void method(java.math.BigDecimal expected, java.lang.String[] values) throws Throwable {
        for (String s : values) {
            BigDecimal tmp = new BigDecimal(s);
            // System.err.println("Testing " + s);
            if (!expected.equals(tmp) || tmp.precision() != 1) {
            }
        }
    }
}

