public class TplClass3546 {

    private static final void method(int min_length, char[] bo, char[] b1) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            bo[i] = (char) (((b1[i] & 0xffff) + 0xffff) >> 1);
        }
    }
}

