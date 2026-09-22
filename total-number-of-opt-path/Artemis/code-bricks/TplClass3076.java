public class TplClass3076 {

    private static final void method(byte[] b2, int min_length, long sad, byte[] b1) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            long x = b1[i];
            long y = b2[i];
            sad += Math.abs(x - y);
        }
    }
}

