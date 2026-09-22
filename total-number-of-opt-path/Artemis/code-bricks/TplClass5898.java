public class TplClass5898 {

    private static final void method(int quotient, long dividend, long divisor, int remainder, int errors) throws Throwable {
        try {
            quotient = Integer.divideUnsigned((int) dividend, (int) divisor);
            errors++;
        } catch (ArithmeticException ea) {
            // Expected
            ;
        }
        try {
            remainder = Integer.remainderUnsigned((int) dividend, (int) divisor);
            errors++;
        } catch (ArithmeticException ea) {
            // Expected
            ;
        }
    }
}

