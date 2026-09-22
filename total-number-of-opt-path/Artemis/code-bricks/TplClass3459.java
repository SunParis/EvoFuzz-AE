public class TplClass3459 {

    private static final void method(short[] bo, short[] b1) throws Throwable {
        int min_length = Math.min(bo.length, b1.length);
        for (int i = 0; i < min_length; i++) {
            bo[i] = (short) (((b1[i] & 0xffff) + 0xffff) >> 1);
        }
    }
}

