public class TplClass2537 {

    private static final void method(int[] a, int[] b, int minus1, int n) throws Throwable {
        for (int i = 1; i < n + minus1; i++) {
            a[i] = b[i + minus1] + b[i] + b[i + 1];
        }
    }
}

