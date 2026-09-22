public class TplClass3073 {

    private static final void method(byte[] b2, int min_length, int sad, byte[] b1) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            sad += Math.abs(b1[i] - b2[i]);
        }
    }
}

