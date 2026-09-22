public class TplClass3843 {

    private static final void method(int[] values) throws Throwable {
        for (int i = 0; i < values.length; ++i) {
            int q = i / 18;
            int r = i % 18;
            values[i] = q + r;
        }
    }
}

