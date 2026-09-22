import java.math.BigDecimal;

public class TplClass7082 {

    private static final void method(java.math.BigDecimal result, int failures, java.math.BigDecimal zero2, java.math.BigDecimal expected, java.math.BigDecimal zero1) throws Throwable {
        if (!(result = zero1.subtract(zero2)).equals(expected)) {
            failures++;
        }
    }
}

