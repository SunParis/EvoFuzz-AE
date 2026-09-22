public class TplClass2828 {

    private static final void method(int[] a, int i, int n) throws Throwable {
        for (int j = 0; j < i; j++) {
            a[j] += 1;
        }
        for (int j = i - 1; j >= 0; j--) {
            a[j] += 1;
        }
        for (int j = i; j < n; j++) {
            a[j] += 1;
        }
        for (int j = n - 1; j >= i; j--) {
            a[j] += 1;
        }
    }
}

