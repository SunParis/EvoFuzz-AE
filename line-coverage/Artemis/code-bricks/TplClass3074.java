public class TplClass3074 {

    private static final void method(byte[] b2, int min_length, int sad, byte[] b1) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            byte s = b1[i];
            byte p = b2[i];
            sad += s >= p ? s - p : p - s;
        }
    }
}

