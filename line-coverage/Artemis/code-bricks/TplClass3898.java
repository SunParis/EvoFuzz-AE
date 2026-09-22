public class TplClass3898 {

    private static final void method(int[] a, int off, int n) throws Throwable {
        // Dynamic peeling to the rescue, aligned vector, cleanup.
        for (int i = 0; i < n; i++) {
            a[off + i] += 1;
        }
    }
}

