import java.math.BigInteger;

public class TplClass5603 {

    private static final void method(java.math.BigInteger dividend, java.math.BigInteger divisor, long remainder, int errors) throws Throwable {
        try {
            remainder = Long.remainderUnsigned(dividend.longValue(), divisor.longValue());
            errors++;
        } catch (ArithmeticException ea) {
            // Expected
            ;
        }
    }
}

