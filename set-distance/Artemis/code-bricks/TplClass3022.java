public class TplClass3022 {

    private static final void method(int[] x, int[] y, int k, int n, int[] interesting) throws Throwable {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                x[k] = interesting[i];
                y[k] = interesting[j];
                k++;
            }
        }
    }
}

