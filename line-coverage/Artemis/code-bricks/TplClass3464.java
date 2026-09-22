public class TplClass3464 {

    private static final void method(byte[] b2, int min_length, byte[] bo, byte[] b1) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            bo[i] = (byte) ((b1[i] + b2[i] + 1) >> 1);
        }
    }
}

