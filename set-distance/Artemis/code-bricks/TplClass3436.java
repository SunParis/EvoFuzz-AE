public class TplClass3436 {

    private static final void method(short[] b2, int min_length, short[] bo, short[] b1) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            int v1 = b1[i] & 0xffff;
            int v2 = b2[i] & 0xffff;
            bo[i] = (short) ((v1 + v2 + 1) >> 1);
        }
    }
}

