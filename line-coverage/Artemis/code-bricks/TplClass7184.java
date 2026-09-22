public class TplClass7184 {

    private static final void method(int length, boolean fail, int[] a, boolean debug) throws Throwable {
        for (int i = 0; i < length; i++) {
            if (a[i] != i) {
                if (debug) {
                }
                fail = true;
            }
        }
    }
}

