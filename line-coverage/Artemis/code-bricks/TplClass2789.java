public class TplClass2789 {

    private static final void method(int[] a, int[] b) throws Throwable {
        for (int j = 0; j < b.length; j++) {
            // Need to know j < b.length < a.length for static bce.
            a[j] += 1;
            // Need to know just j < b.length for static bce.
            b[j] += 1;
        }
    }
}

