public class TplClass3021 {

    private static final void method(int[] x, int[] y, int min_length, long sad) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            long s = x[i];
            long p = y[i];
            sad += Math.abs(s - p);
        }
    }
}

