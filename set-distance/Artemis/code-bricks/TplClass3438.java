public class TplClass3438 {

    private static final void method(int min_length, short[] bo, short[] b1) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            bo[i] = (short) ((b1[i] + 0x7fff) >> 1);
        }
    }
}

