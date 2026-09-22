import java.math.MathContext;
import java.math.RoundingMode;
import java.math.BigDecimal;

public class TplClass7025 {

    private static final void method(int mpc, int failures, java.lang.String[] value) throws Throwable {
        for (int i = 0; i < value.length; i++) {
            for (int j = 0; j < value.length; j++) {
                BigDecimal v1 = new BigDecimal(value[i]);
                BigDecimal v2 = new BigDecimal(value[j]);
                BigDecimal res1 = v1.divide(v2, new MathContext(mpc, RoundingMode.HALF_EVEN));
                BigDecimal res2 = v1.divide(v2, new MathContext(128, RoundingMode.HALF_EVEN)).round(new MathContext(mpc, RoundingMode.HALF_EVEN));
                if (!res1.equals(res2)) {
                    failures++;
                }
            }
        }
    }
}

