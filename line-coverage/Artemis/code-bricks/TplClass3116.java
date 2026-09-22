public class TplClass3116 {

    private static final void method(int i, short[] s, int sad) throws Throwable {
        // narrower part sign extends
        short x = (short) (s[i] + 110);
        sad += Math.abs(x - s[i]);
    }
}

