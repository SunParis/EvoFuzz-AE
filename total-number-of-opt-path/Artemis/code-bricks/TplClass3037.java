public class TplClass3037 {

    private static final void method(int min_length, short[] s1, long sad, short[] s2) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            long x = s1[i];
            long y = s2[i];
            sad += Math.abs(x - y);
        }
    }
}

