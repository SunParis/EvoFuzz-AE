import java.math.MathContext;
import java.math.BigInteger;
import java.math.BigDecimal;

public class TplClass7097 {

    private static final void method(int failures, java.math.BigDecimal zero2, java.math.MathContext longEnough, java.math.BigDecimal zero1) throws Throwable {
        BigDecimal expected = new BigDecimal(BigInteger.ZERO, Math.max(zero1.scale(), zero2.scale()));
        BigDecimal result;
        if (!(result = zero1.add(zero2)).equals(expected)) {
            failures++;
        }
        if (!(result = zero1.add(zero2, MathContext.UNLIMITED)).equals(expected)) {
            failures++;
        }
        if (!(result = zero1.add(zero2, longEnough)).equals(expected)) {
            failures++;
        }
    }
}

