public class TplClass56 {

    private static final void method(int i, byte[] bt, byte[] b, boolean failed, int l) throws Throwable {
        for (int k = 0; k < l; k++) {
            if (bt[k] != b[i + k - 1]) {
                failed = true;
            }
        }
    }
}

