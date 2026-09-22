public class TplClass3056 {

    private static final void method(int min_length, char[] s1, long sad, char[] s2) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            long x = s1[i];
            long y = s2[i];
            sad += Math.abs(x - y);
        }
    }
}

