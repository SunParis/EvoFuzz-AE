public class TplClass3441 {

    private static final void method(short[] b2, short[] bo, short[] b1) throws Throwable {
        int min_length = Math.min(bo.length, Math.min(b1.length, b2.length));
        for (int i = 0; i < min_length; i++) {
            bo[i] = (short) ((b1[i] + b2[i]) >> 1);
        }
    }
}

