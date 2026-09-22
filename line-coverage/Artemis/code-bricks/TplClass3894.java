public class TplClass3894 {

    private static final void method(int[] a) throws Throwable {
        // So no peeling, aligned vector, cleanup.
        for (int i = 1; i < a.length; i++) {
            a[i] += 1;
        }
    }
}

