import java.math.BigInteger;
import java.math.BigDecimal;

public class TplClass7012 {

    private static final void method() throws Throwable {
        for (int i = -10; i < 10; i++) {
            BigDecimal bd = BigDecimal.ONE.scaleByPowerOfTen(i);
            BigDecimal expected;
            if (!bd.equals(expected = new BigDecimal(BigInteger.ONE, -i))) {
            }
            bd = BigDecimal.ONE.negate().scaleByPowerOfTen(i);
            if (!bd.equals(expected = new BigDecimal(BigInteger.ONE.negate(), -i))) {
            }
        }
    }
}

