public class TplClass3893 {

    private static final void method(int[] a) throws Throwable {
        // So no peeling, aligned vector, no cleanup.
        for (int i = 1; i < 9; i++) {
            a[i] += 1;
        }
    }
}

