public class TplClass5876 {

    private static final void method(int quotient, long dividend, long divisor, int errors) throws Throwable {
        try {
            quotient = Integer.divideUnsigned((int) dividend, (int) divisor);
            errors++;
        } catch (ArithmeticException ea) {
            // Expected
            ;
        }
    }
}

