import java.math.MathContext;
import java.math.BigInteger;
import java.math.BigDecimal;

public class TplClass7104 {

    private static final void method(int failures, java.math.BigDecimal value, java.math.MathContext longEnough, java.math.BigDecimal zero1) throws Throwable {
        BigDecimal expected = new BigDecimal(BigInteger.ZERO, (int) Math.min(Math.max((long) zero1.scale() + value.scale(), Integer.MIN_VALUE), Integer.MAX_VALUE));
        BigDecimal result;
        if (!(result = zero1.multiply(value)).equals(expected)) {
            failures++;
        }
        if (!(result = zero1.multiply(value, MathContext.UNLIMITED)).equals(expected)) {
            failures++;
        }
        if (!(result = zero1.multiply(value, longEnough)).equals(expected)) {
            failures++;
        }
    }
}

