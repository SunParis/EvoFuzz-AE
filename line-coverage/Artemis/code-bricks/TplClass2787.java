public class TplClass2787 {

    private static final void method(int result, int[] x) throws Throwable {
        // Induction is done in byte precision, but fits.
        for (byte i = 0; i < 10; i++) {
            result += x[i];
        }
    }
}

