public class TplClass3508 {

    private static final void method(int min_length, short[] bo, short[] b1) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            bo[i] = (short) (((b1[i] & 0xffff) + 0xffff) >>> 1);
        }
    }
}

