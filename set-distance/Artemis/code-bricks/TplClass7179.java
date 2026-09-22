import java.math.BigDecimal;

public class TplClass7179 {

    private static final void method(java.math.BigDecimal x, int failures) throws Throwable {
        try {
            // 
            // The string representation "1e2147483647", which is equivalent
            // to 10^Integer.MAX_VALUE, is used to create an augend with an
            // unscaled value of 1 and a scale of -Integer.MAX_VALUE. The
            // addend "1" has an unscaled value of 1 with a scale of 0. The
            // addition is performed exactly and is specified to have a
            // preferred scale of max(-Integer.MAX_VALUE, 0). As the scale
            // of the result is 0, a value with Integer.MAX_VALUE + 1 digits
            // would need to be created. Therefore the next statement is
            // expected to overflow with an ArithmeticException.
            // 
            x = new BigDecimal("1e2147483647").add(new BigDecimal(1));
            failures++;
        } catch (ArithmeticException ae) {
        }
    }
}

