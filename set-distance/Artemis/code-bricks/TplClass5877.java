public class TplClass5877 {

    private static final void method(long dividend, long divisor, int remainder, int errors) throws Throwable {
        try {
            remainder = Integer.remainderUnsigned((int) dividend, (int) divisor);
            errors++;
        } catch (ArithmeticException ea) {
            // Expected
            ;
        }
    }
}

