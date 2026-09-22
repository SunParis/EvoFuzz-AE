public class TplClass3120 {

    private static final void method(int min_length, char[] s1, short sad, char[] s2) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            short s = (short) s1[i];
            short p = (short) s2[i];
            int x = s - p;
            if (x < 0)
                x = -x;
            sad += x;
        }
    }
}

