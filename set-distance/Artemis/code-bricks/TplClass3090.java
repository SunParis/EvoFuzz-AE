public class TplClass3090 {

    private static final void method(long[] x, long[] y, int min_length, long sad) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            sad += Math.abs(x[i] - y[i]);
        }
    }
}

