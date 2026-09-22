public class TplClass2825 {

    private static final void method(int[] a, int[] b, int j) throws Throwable {
        // Need to know j < b.length < a.length for static bce.
        a[j] += 1;
        // Need to know just j < b.length for static bce.
        b[j] += 1;
    }
}

