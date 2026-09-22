public class TplClass3536 {

    private static final void method(char[] bo, char[] b1) throws Throwable {
        int min_length = Math.min(bo.length, b1.length);
        for (int i = 0; i < min_length; i++) {
            bo[i] = (char) ((b1[i] + 0xffff) >>> 1);
        }
    }
}

