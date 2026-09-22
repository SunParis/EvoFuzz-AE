public class TplClass3844 {

    private static final void method(long[] values) throws Throwable {
        for (int i = 0; i < values.length; ++i) {
            long d = (long) i;
            long q = d / 18L;
            long r = d % 18L;
            values[i] = q + r;
        }
    }
}

