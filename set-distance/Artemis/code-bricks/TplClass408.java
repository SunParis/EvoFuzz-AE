public class TplClass408 {

    private static final void method(int[] a, int[] b, int j) throws Throwable {
        // recompiled without optimistic optimizations
        for (int i = 0; i < 10; i++) {
            a[i] = i;
        }
        // The test itself
        a[j] = 0;
        a[j + 5] = 0;
        // this range check shouldn't be eliminated
        b[j + 4] = 0;
    }
}

