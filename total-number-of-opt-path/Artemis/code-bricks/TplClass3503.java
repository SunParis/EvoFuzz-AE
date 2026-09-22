public class TplClass3503 {

    private static final void method(short[] b2, int min_length, short[] bo, short[] b1) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            bo[i] = (short) ((b1[i] + b2[i]) >>> 1);
        }
    }
}

