public class TplClass3765 {

    private static final void method(int cnt, long t, long[] w) throws Throwable {
        for (int i = 2; i < cnt; ++i) {
            w[i] = w[i - 1] + w[i - 2];
            t = w[i];
        }
    }
}

