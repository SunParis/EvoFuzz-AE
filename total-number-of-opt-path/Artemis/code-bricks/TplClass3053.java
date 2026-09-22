public class TplClass3053 {

    private static final void method(int min_length, char[] s1, int sad, char[] s2) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            sad += Math.abs(s1[i] - s2[i]);
        }
    }
}

