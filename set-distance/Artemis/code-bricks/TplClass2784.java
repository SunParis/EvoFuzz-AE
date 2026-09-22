public class TplClass2784 {

    private static final void method(int result, int[] x) throws Throwable {
        // but truncated at the use as subscript.
        for (long i = 0; i < x.length; i++) {
            result += x[(int) i];
        }
    }
}

