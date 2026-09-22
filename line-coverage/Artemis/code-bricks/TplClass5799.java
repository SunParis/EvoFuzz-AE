public class TplClass5799 {

    private static final void method(byte[] buf, int hash, int off, int n) throws Throwable {
        for (int i = 0; i < n; i++) {
            int value = buf[off + i];
            hash = hash ^ value;
        }
    }
}

