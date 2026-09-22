import java.math.MathContext;
import java.math.BigInteger;
import java.math.BigDecimal;

public class TplClass7105 {

    private static final void method(java.math.BigDecimal zero, int failures, java.math.BigDecimal one, java.math.MathContext longEnough) throws Throwable {
        BigDecimal expected = new BigDecimal(BigInteger.ZERO, (int) Math.min(Math.max((long) zero.scale() - one.scale(), Integer.MIN_VALUE), Integer.MAX_VALUE));
        BigDecimal result;
        if (!(result = zero.divide(one)).equals(expected)) {
            failures++;
        }
        if (!(result = zero.divide(one, MathContext.UNLIMITED)).equals(expected)) {
            failures++;
        }
        if (!(result = zero.divide(one, longEnough)).equals(expected)) {
            failures++;
        }
    }
}

