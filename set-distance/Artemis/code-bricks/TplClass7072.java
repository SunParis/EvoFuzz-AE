import java.math.BigDecimal;

public class TplClass7072 {

    private static final void method(java.math.BigDecimal[] zeros, int failures) throws Throwable {
        for (BigDecimal zero : zeros) {
            BigDecimal result;
            BigDecimal expected = BigDecimal.valueOf(1, zero.scale());
            if (!(result = zero.ulp()).equals(expected)) {
                failures++;
            }
        }
    }
}

