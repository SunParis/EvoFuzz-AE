public class TplClass2727 {

    private static final void method(int expectedThrows, float f) throws Throwable {
        /*
         * Floating point divide by zero doesn't throw an exception -- the
         * result is just NaN.
         */
        try {
            f = 10.0f / f;
        } catch (ArithmeticException ae) {
            expectedThrows--;
        }
    }
}

