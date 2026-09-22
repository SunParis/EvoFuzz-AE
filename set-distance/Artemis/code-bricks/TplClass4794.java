import java.math.BigInteger;

public class TplClass4794 {

    private static final void method(int errors, java.math.BigInteger bi) throws Throwable {
        try {
            int value = bi.intValueExact();
            errors++;
        } catch (ArithmeticException ae) {
            // Expected
            ;
        }
    }
}

