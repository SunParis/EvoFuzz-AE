public class TplClass2797 {

    private static final void method(int[] a, int n) throws Throwable {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                a[j] += 1;
            }
            for (int j = i; j >= 0; j--) {
                a[j] += 1;
            }
            for (int j = i + 1; j < n; j++) {
                a[j] += 1;
            }
            for (int j = n - 1; j >= i + 1; j--) {
                a[j] += 1;
            }
        }
    }
}

