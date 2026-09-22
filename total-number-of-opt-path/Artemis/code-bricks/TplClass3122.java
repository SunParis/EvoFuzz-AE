public class TplClass3122 {

    private static final void method(int min_length, char[] s1, int sad, char[] s2) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            short s = (short) s1[i];
            short p = (short) s2[i];
            sad += s >= p ? s - p : p - s;
        }
    }
}

