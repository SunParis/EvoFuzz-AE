public class TplClass3107 {

    private static final void method(short[] s, int sad) throws Throwable {
        for (int i = 0; i < s.length; i++) {
            // narrower part sign extends
            short x = (short) (s[i] + 110);
            sad += Math.abs(x - s[i]);
        }
    }
}

