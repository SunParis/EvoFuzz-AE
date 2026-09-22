public class TplClass3486 {

    private static final void method(byte[] b2, int min_length, byte[] bo, byte[] b1) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            bo[i] = (byte) (((b1[i] & 0xff) + (b2[i] & 0xff)) >>> 1);
        }
    }
}

