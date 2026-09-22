public class TplClass5806 {

    private static final void method(byte[] buf, int total, int hash, int off, int n) throws Throwable {
        if (n > 0) {
            total += n;
            for (int i = 0; i < n; i++) {
                int value = buf[off + i];
                hash = hash ^ value;
            }
        }
    }
}

