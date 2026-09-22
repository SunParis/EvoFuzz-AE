public class TplClass3093 {

    private static final void method(long[] x, long[] y, int k, int n, long[] interesting) throws Throwable {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                x[k] = interesting[i];
                y[k] = interesting[j];
                k++;
            }
        }
    }
}

