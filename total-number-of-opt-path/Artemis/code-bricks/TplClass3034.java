public class TplClass3034 {

    private static final void method(int min_length, short[] s1, int sad, short[] s2) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            short s = s1[i];
            short p = s2[i];
            sad += s >= p ? s - p : p - s;
        }
    }
}

