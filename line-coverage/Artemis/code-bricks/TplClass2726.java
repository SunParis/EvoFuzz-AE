public class TplClass2726 {

    private static final void method(int expectedThrows, long j) throws Throwable {
        try {
            j = 10L / 0L;
        } catch (ArithmeticException ae) {
            expectedThrows--;
        }
    }
}

