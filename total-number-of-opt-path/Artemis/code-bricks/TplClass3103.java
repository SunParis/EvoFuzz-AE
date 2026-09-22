public class TplClass3103 {

    private static final void method(short[] s, int sad) throws Throwable {
        for (int i = 0; i < s.length; i++) {
            sad += Math.abs(32767 - s[i]);
        }
    }
}

