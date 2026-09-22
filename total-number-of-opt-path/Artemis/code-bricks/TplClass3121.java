public class TplClass3121 {

    private static final void method(int min_length, char[] s1, int sad, char[] s2) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            sad += Math.abs(((short) s1[i]) - ((short) s2[i]));
        }
    }
}

