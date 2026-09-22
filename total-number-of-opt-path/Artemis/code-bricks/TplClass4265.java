public class TplClass4265 {

    private static final void method(int x, int[] a, int n) throws Throwable {
        // BCE applies, but hoisting would crash the loop.
        for (int i = -10000; i < 10000; i++) {
            for (int j = 0; j <= 1; j++) {
                if (0 <= i && i < n)
                    x += a[i];
            }
        }
    }
}

