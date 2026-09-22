public class TplClass3895 {

    private static final void method(int[] a) throws Throwable {
        // Static peeling to the rescue, aligned vector, no cleanup.
        for (int i = 0; i < 9; i++) {
            a[i] += 1;
        }
    }
}

