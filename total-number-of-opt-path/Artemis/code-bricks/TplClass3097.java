public class TplClass3097 {

    private static final void method(long[] x, long[] y, int i, long sad) throws Throwable {
        long s = x[i];
        long p = y[i];
        sad += s >= p ? s - p : p - s;
    }
}

