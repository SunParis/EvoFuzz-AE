public class TplClass3018 {

    private static final void method(int[] x, int[] y, int min_length, int sad) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            sad += Math.abs(x[i] - y[i]);
        }
    }
}

