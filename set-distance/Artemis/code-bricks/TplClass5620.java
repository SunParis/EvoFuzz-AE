import java.math.BigInteger;

public class TplClass5620 {

    private static final void method(java.math.BigInteger dividend, java.math.BigInteger divisor, int errors) throws Throwable {
        long quotient;
        BigInteger longQuotient;
        long remainder;
        BigInteger longRemainder;
        if (divisor.equals(BigInteger.ZERO)) {
            try {
                quotient = Long.divideUnsigned(dividend.longValue(), divisor.longValue());
                errors++;
            } catch (ArithmeticException ea) {
                // Expected
                ;
            }
            try {
                remainder = Long.remainderUnsigned(dividend.longValue(), divisor.longValue());
                errors++;
            } catch (ArithmeticException ea) {
                // Expected
                ;
            }
        } else {
            quotient = Long.divideUnsigned(dividend.longValue(), divisor.longValue());
            longQuotient = dividend.divide(divisor);
            if (quotient != longQuotient.longValue()) {
                errors++;
            }
            remainder = Long.remainderUnsigned(dividend.longValue(), divisor.longValue());
            longRemainder = dividend.remainder(divisor);
            if (remainder != longRemainder.longValue()) {
                errors++;
            }
        }
    }
}

