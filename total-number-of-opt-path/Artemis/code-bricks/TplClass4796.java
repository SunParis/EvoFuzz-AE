import java.math.BigInteger;

public class TplClass4796 {

    private static final void method(int errors, java.math.BigInteger bi) throws Throwable {
        try {
            int value = bi.byteValueExact();
            errors++;
        } catch (ArithmeticException ae) {
            // Expected
            ;
        }
    }
}

