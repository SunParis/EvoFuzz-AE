import java.math.BigInteger;
import java.math.BigDecimal;

public class TplClass7095 {

    private static final void method(java.math.BigDecimal[] zeros) throws Throwable {
        for (int i = 0; i < 21; i++) {
            zeros[i] = new BigDecimal(BigInteger.ZERO, i - 10);
        }
        zeros[21] = new BigDecimal(BigInteger.ZERO, Integer.MIN_VALUE);
        zeros[22] = new BigDecimal(BigInteger.ZERO, Integer.MAX_VALUE);
    }
}

