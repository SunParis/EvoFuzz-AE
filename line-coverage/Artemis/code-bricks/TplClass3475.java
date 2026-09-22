public class TplClass3475 {

    private static final void method(byte[] b2, byte[] bo, byte[] b1) throws Throwable {
        int min_length = Math.min(bo.length, Math.min(b1.length, b2.length));
        for (int i = 0; i < min_length; i++) {
            bo[i] = (byte) ((b1[i] + b2[i] + 1) >> 1);
        }
    }
}

