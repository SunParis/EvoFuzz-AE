public class TplClass3553 {

    private static final void method(char[] b2, char[] bo, char[] b1) throws Throwable {
        int min_length = Math.min(bo.length, Math.min(b1.length, b2.length));
        for (int i = 0; i < min_length; i++) {
            bo[i] = (char) (((b1[i] & 0xffff) + (b2[i] & 0xffff) + 1) >> 1);
        }
    }
}

