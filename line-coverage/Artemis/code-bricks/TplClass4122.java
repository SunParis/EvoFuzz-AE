public class TplClass4122 {

    private static final void method(int[] a, int[] b, int c) throws Throwable {
        // Dynamic bce on b requires two deopts: one null and one bound.
        for (int i = 0; i < a.length; i++) {
            a[i] = b[c];
        }
    }
}

