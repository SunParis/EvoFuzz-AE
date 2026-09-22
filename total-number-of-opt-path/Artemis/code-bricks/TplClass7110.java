import java.math.BigDecimal;

public class TplClass7110 {

    private static final void method(java.math.BigDecimal zero, int failures) throws Throwable {
        BigDecimal result;
        BigDecimal expected = BigDecimal.valueOf(1, zero.scale());
        if (!(result = zero.ulp()).equals(expected)) {
            failures++;
        }
    }
}

