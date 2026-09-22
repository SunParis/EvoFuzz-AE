import java.math.MathContext;
import java.math.BigInteger;
import java.math.BigDecimal;

public class TplClass7058 {

    private static final void method(int failures, java.math.BigDecimal[] zeros, java.math.MathContext longEnough, java.math.BigDecimal zero1) throws Throwable {
        for (BigDecimal zero2 : zeros) {
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
}

