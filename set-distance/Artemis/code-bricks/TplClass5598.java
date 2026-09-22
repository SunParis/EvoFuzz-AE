import java.math.BigInteger;

public class TplClass5598 {

    private static final void method(java.math.BigInteger longRemainder, long quotient, java.math.BigInteger divisor, java.math.BigInteger dividend, long remainder, int errors, java.math.BigInteger longQuotient) throws Throwable {
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

