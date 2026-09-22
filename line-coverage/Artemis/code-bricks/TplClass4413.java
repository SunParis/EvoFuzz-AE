public class TplClass4413 {

    private static final void method(int[] a, int[] b, int MAX, int n) throws Throwable {
        for (int i = 0; i < n; ++i) {
            int j = a[i];
            b[i] = (j > MAX ? MAX : 0);
        }
    }
}

