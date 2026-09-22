public class TplClass2725 {

    private static final void method(int i, int expectedThrows) throws Throwable {
        try {
            i = 10 / 0;
        } catch (ArithmeticException ae) {
            expectedThrows--;
        }
    }
}

