public class TplClass4414 {

    private static final void method(int[] a, int n) throws Throwable {
        for (int i = 0; i < n - 1; ++i) {
            if (a[i] < a[i + 1]) {
                int tmp = a[i];
                a[i] = a[i + 1];
                a[i + 1] = tmp;
            }
        }
    }
}

