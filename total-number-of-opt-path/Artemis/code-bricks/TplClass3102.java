public class TplClass3102 {

    private static final void method(short[] s, int sad) throws Throwable {
        for (int i = 0; i < s.length; i++) {
            sad += Math.abs(s[i] - 32767);
        }
    }
}

