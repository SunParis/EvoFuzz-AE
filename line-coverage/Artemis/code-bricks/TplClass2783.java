public class TplClass2783 {

    private static final void method(int result, int[] x) throws Throwable {
        // but truncated at the use as subscript.
        for (long i = 0; i < 10; i++) {
            result += x[(int) i];
        }
    }
}

