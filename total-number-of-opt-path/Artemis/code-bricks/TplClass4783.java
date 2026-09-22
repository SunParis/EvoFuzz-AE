import java.math.BigInteger;

public class TplClass4783 {

    private static final void method(java.math.BigInteger[] outOfRange, int errors) throws Throwable {
        for (BigInteger bi : outOfRange) {
            try {
                int value = bi.intValueExact();
                errors++;
            } catch (ArithmeticException ae) {
                // Expected
                ;
            }
        }
    }
}

