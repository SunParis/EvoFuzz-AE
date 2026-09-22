import java.math.BigInteger;

public class TplClass5602 {

    private static final void method(long quotient, java.math.BigInteger dividend, java.math.BigInteger divisor, int errors) throws Throwable {
        try {
            quotient = Long.divideUnsigned(dividend.longValue(), divisor.longValue());
            errors++;
        } catch (ArithmeticException ea) {
            // Expected
            ;
        }
    }
}

