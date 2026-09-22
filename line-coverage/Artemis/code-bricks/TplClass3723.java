public class TplClass3723 {

    private static final void method() throws Throwable {
        int[] a = new int[10];
        for (int i = 0; i < a.length; ++i) {
            // unused but throwing
            int x = a[11];
            a[i] = 42;
        }
    }
}

