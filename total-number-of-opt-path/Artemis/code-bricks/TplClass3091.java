public class TplClass3091 {

    private static final void method(long[] x, long[] y, int min_length, long sad) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            long s = x[i];
            long p = y[i];
            sad += s >= p ? s - p : p - s;
        }
    }
}

