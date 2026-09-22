import java.math.BigInteger;

public class TplClass4787 {

    private static final void method(java.math.BigInteger[] outOfRange, int errors) throws Throwable {
        for (BigInteger bi : outOfRange) {
            try {
                int value = bi.byteValueExact();
                errors++;
            } catch (ArithmeticException ae) {
                // Expected
                ;
            }
        }
    }
}

