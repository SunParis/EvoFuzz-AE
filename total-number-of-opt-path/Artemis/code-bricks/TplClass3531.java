public class TplClass3531 {

    private static final void method(char[] b2, int i, char[] bo, char[] b1) throws Throwable {
        bo[i] = (char) (((b1[i] & 0xffff) + (b2[i] & 0xffff)) >>> 1);
    }
}

