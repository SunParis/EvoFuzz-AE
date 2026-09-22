public class TplClass2538 {

    private static final void method(int[] a, int[] b, int plus1, int n) throws Throwable {
        for (int i = 1; i < n - plus1; i++) {
            a[i] = b[i - plus1] + b[i] + b[i + 1];
        }
    }
}

