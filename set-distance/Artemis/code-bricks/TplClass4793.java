import java.math.BigInteger;

public class TplClass4793 {

    private static final void method(int errors, java.math.BigInteger bi) throws Throwable {
        try {
            long value = bi.longValueExact();
            errors++;
        } catch (ArithmeticException ae) {
            // Expected
            ;
        }
    }
}

