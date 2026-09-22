public class TplClass2536 {

    private static final void method(int[] a, int[] b, int n) throws Throwable {
        for (int i = 1; i < n - 1; i++) {
            a[i] = b[i - 1] + b[i] + b[i + 1];
        }
    }
}

