public class TplClass3032 {

    private static final void method(int min_length, short[] s1, short sad, short[] s2) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            short s = s1[i];
            short p = s2[i];
            int x = s - p;
            if (x < 0)
                x = -x;
            sad += x;
        }
    }
}

