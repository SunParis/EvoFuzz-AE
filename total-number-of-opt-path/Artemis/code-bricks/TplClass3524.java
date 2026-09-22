public class TplClass3524 {

    private static final void method(char[] b2, int min_length, char[] bo, char[] b1) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            bo[i] = (char) (((b1[i] & 0xffff) + (b2[i] & 0xffff) + 1) >>> 1);
        }
    }
}

