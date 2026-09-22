public class TplClass3027 {

    private static final void method(int[] x, int[] y, int i, int sad) throws Throwable {
        int s = x[i];
        int p = y[i];
        int m = s - p;
        if (m < 0)
            m = -m;
        sad += m;
    }
}

