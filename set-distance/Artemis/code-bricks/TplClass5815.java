public class TplClass5815 {

    private static final void method(byte[] buf, int total, int hash, int off, int n) throws Throwable {
        total += n;
        for (int i = 0; i < n; i++) {
            int value = buf[off + i];
            hash = hash ^ value;
        }
    }
}

