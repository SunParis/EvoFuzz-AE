import java.math.BigDecimal;

public class TplClass7047 {

    private static final void method() throws Throwable {
        String[] values = { "00004e5", "\u0660\u0660\u0660\u06604e5" };
        BigDecimal expected = new BigDecimal("4e5");
        for (String s : values) {
            BigDecimal tmp = new BigDecimal(s);
            // System.err.println("Testing " + s);
            if (!expected.equals(tmp) || tmp.precision() != 1) {
            }
        }
    }
}

