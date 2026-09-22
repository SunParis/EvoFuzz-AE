public class TplClass3020 {

    private static final void method(int[] x, int[] y, int min_length, int sad) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            int s = x[i];
            int p = y[i];
            int m = s - p;
            if (m < 0)
                m = -m;
            sad += m;
        }
    }
}

