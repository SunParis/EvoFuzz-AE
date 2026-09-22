public class TplClass4123 {

    private static final void method(int[] a, int[] b, int c) throws Throwable {
        // Dynamic bce on b requires three deopts: one null and two bounds.
        for (int i = 0; i < a.length; i++) {
            a[i] = b[c - 1] + b[c] + b[c + 1];
        }
    }
}

