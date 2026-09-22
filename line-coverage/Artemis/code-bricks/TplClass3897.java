public class TplClass3897 {

    private static final void method(int[] a, int off) throws Throwable {
        // Dynamic peeling to the rescue, aligned vector, cleanup.
        for (int i = 0; i < 9; i++) {
            a[off + i] += 1;
        }
    }
}

