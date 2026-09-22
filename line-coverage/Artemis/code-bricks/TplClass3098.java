public class TplClass3098 {

    private static final void method(long[] x, long[] y, int i, long sad) throws Throwable {
        long s = x[i];
        long p = y[i];
        long m = s - p;
        if (m < 0)
            m = -m;
        sad += m;
    }
}

