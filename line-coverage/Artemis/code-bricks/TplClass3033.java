public class TplClass3033 {

    private static final void method(int min_length, short[] s1, int sad, short[] s2) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            sad += Math.abs(s1[i] - s2[i]);
        }
    }
}

