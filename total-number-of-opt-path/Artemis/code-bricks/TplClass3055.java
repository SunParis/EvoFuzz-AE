public class TplClass3055 {

    private static final void method(int min_length, char[] s1, int sad, char[] s2) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            char s = s1[i];
            char p = s2[i];
            int x = s - p;
            if (x < 0)
                x = -x;
            sad += x;
        }
    }
}

