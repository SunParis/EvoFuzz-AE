public class TplClass4124 {

    private static final void method(int[] a, int[] b) throws Throwable {
        // Dynamic bce on b requires three deopts: one null and two bounds.
        for (int i = 0; i < a.length; i++) {
            a[i] = b[i];
        }
    }
}

