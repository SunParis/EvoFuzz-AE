public class TplClass3720 {

    private static final void method(int[] a) throws Throwable {
        for (int i = 0; i < a.length; ++i) {
            // unused but throwing
            int x = a[11];
            a[i] = 42;
        }
    }
}

