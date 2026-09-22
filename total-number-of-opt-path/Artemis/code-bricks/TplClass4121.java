public class TplClass4121 {

    private static final void method(int[] a, int[] b) throws Throwable {
        // Dynamic bce on b requires two deopts: one null and one bound.
        for (int i = 0; i < a.length; i++) {
            a[i] = b[0] + b[1] + b[2];
        }
    }
}

