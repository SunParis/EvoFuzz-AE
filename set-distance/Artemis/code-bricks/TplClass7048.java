import java.math.BigDecimal;

public class TplClass7048 {

    private static final void method(java.lang.String s, java.math.BigDecimal expected) throws Throwable {
        BigDecimal tmp = new BigDecimal(s);
        // System.err.println("Testing " + s);
        if (!expected.equals(tmp) || tmp.precision() != 1) {
        }
    }
}

