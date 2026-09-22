import java.math.BigInteger;

public class TplClass4781 {

    private static final void method(java.math.BigInteger[] outOfRange, int errors) throws Throwable {
        for (BigInteger bi : outOfRange) {
            try {
                long value = bi.longValueExact();
                errors++;
            } catch (ArithmeticException ae) {
                // Expected
                ;
            }
        }
    }
}

