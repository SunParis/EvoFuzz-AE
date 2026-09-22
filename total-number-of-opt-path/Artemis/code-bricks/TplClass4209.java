public class TplClass4209 {

    private static final void method(int[] a, int j, int k) throws Throwable {
        for (int i = 1; i < 10; i++) {
            j = i - 1;
            // b/32547652: after DCE, bounds checks become consecutive,
            // and second should not be revisited after forward BCE.
            k = a[i] + a[i - 1];
        }
    }
}

