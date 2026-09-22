public class TplClass3759 {

    private static final void method(int[] a, int n) throws Throwable {
        for (int i = 0; i < n; ++i) {
            // Cannot be eliminated due to aliasing.
            a[i] = 1;
        }
    }
}

