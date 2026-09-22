public class TplClass3019 {

    private static final void method(int[] x, int[] y, int min_length, int sad) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            int s = x[i];
            int p = y[i];
            sad += s >= p ? s - p : p - s;
        }
    }
}

