public class TplClass3719 {

    private static final void method(int[] a) throws Throwable {
        for (int i = 0; i < a.length; ++i) {
            // unused but throwing
            int x = 42 / 0;
            a[i] = 42;
        }
    }
}

