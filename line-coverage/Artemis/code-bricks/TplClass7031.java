import java.math.MathContext;
import java.math.BigDecimal;

public class TplClass7031 {

    private static final void method(int i, int j, int failures, java.lang.String[][] results, java.lang.String[] value) throws Throwable {
        BigDecimal v1 = new BigDecimal(value[i]);
        BigDecimal v2 = new BigDecimal(value[j]);
        BigDecimal res1 = v1.divide(v2, MathContext.DECIMAL64);
        if (!res1.toString().equals(results[i][j])) {
            failures++;
        }
    }
}

