public class TplClass2728 {

    private static final void method(double d, int expectedThrows) throws Throwable {
        try {
            d = 10.0 / d;
        } catch (ArithmeticException ae) {
            expectedThrows--;
        }
    }
}

