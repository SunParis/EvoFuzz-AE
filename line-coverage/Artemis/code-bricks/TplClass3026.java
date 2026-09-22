public class TplClass3026 {

    private static final void method(int[] x, int[] y, int i, int sad) throws Throwable {
        int s = x[i];
        int p = y[i];
        sad += s >= p ? s - p : p - s;
    }
}

