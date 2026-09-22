public class TplClass2790 {

    private static final void method(int[] a, int i, int[] b) throws Throwable {
        for (int j = 0; j < i; j++) {
            // Need to know j < i < a.length for static bce.
            a[j] += 1;
            // Need to know just j < i for static bce.
            b[j] += 1;
        }
    }
}

