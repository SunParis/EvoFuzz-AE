public class TplClass3466 {

    private static final void method(int min_length, byte[] bo, byte[] b1) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            bo[i] = (byte) ((b1[i] + 0x7f) >> 1);
        }
    }
}

