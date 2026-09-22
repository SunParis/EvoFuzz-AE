public class TplClass2785 {

    private static final void method(int result, int[] x) throws Throwable {
        // Induction is done in short precision, but fits.
        for (short i = 0; i < 10; i++) {
            result += x[i];
        }
    }
}

