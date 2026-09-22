import java.math.BigInteger;

public class TplClass5624 {

    private static final void method(java.math.BigInteger longRemainder, long quotient, java.math.BigInteger divisor, java.math.BigInteger dividend, long remainder, int errors, java.math.BigInteger longQuotient) throws Throwable {
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

