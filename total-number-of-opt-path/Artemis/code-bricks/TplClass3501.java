public class TplClass3501 {

    private static final void method(byte[] bo, byte[] b1) throws Throwable {
        int min_length = Math.min(bo.length, b1.length);
        for (int i = 0; i < min_length; i++) {
            bo[i] = (byte) (((b1[i] & 0xff) + 0xff) >>> 1);
        }
    }
}

