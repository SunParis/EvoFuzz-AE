import java.math.BigInteger;

public class TplClass5621 {

    private static final void method(long quotient, java.math.BigInteger dividend, java.math.BigInteger divisor, long remainder, int errors) throws Throwable {
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
    }
}

