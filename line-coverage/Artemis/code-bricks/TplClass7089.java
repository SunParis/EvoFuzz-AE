import java.math.BigDecimal;

public class TplClass7089 {

    private static final void method(java.math.BigDecimal result, java.math.BigDecimal zero, int failures, java.math.BigDecimal expected, java.math.BigDecimal one) throws Throwable {
        if (!(result = zero.divide(one)).equals(expected)) {
            failures++;
        }
    }
}

