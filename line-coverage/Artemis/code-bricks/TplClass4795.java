import java.math.BigInteger;

public class TplClass4795 {

    private static final void method(int errors, java.math.BigInteger bi) throws Throwable {
        try {
            int value = bi.shortValueExact();
            errors++;
        } catch (ArithmeticException ae) {
            // Expected
            ;
        }
    }
}

