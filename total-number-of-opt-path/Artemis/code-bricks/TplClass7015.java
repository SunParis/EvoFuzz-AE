import java.math.BigInteger;
import java.math.BigDecimal;

public class TplClass7015 {

    private static final void method(int i) throws Throwable {
        BigDecimal bd = BigDecimal.ONE.scaleByPowerOfTen(i);
        BigDecimal expected;
        if (!bd.equals(expected = new BigDecimal(BigInteger.ONE, -i))) {
        }
        bd = BigDecimal.ONE.negate().scaleByPowerOfTen(i);
        if (!bd.equals(expected = new BigDecimal(BigInteger.ONE.negate(), -i))) {
        }
    }
}

