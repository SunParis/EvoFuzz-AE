public class TplClass2729 {

    private static final void method() throws Throwable {
        int expectedThrows = 2;
        int i;
        long j;
        float f = 0.0f;
        double d = 0.0;
        try {
            i = 10 / 0;
        } catch (ArithmeticException ae) {
            expectedThrows--;
        }
        try {
            j = 10L / 0L;
        } catch (ArithmeticException ae) {
            expectedThrows--;
        }
        /*
         * Floating point divide by zero doesn't throw an exception -- the
         * result is just NaN.
         */
        try {
            f = 10.0f / f;
        } catch (ArithmeticException ae) {
            expectedThrows--;
        }
        try {
            d = 10.0 / d;
        } catch (ArithmeticException ae) {
            expectedThrows--;
        }
        if (expectedThrows != 0)
            ;
    }
}

