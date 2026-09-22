public class TplClass3896 {

    private static final void method(int[] a) throws Throwable {
        // Static peeling to the rescue, aligned vector, cleanup.
        for (int i = 0; i < a.length; i++) {
            a[i] += 1;
        }
    }
}

