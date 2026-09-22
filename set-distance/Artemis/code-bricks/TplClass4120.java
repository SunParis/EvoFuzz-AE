public class TplClass4120 {

    private static final void method(int[] a, int[] b) throws Throwable {
        // Dynamic bce on b requires two deopts: one null and one bound.
        for (int i = 0; i < a.length; i++) {
            a[i] = b[1];
        }
    }
}

