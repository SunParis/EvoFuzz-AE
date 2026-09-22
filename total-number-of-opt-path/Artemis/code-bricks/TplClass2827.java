public class TplClass2827 {

    private static final void method(int[] a, int[] b, int j) throws Throwable {
        // Need to know j < i < n for static bce.
        a[j] += 1;
        // Need to know just j < i for static bce.
        b[j] += 1;
    }
}

