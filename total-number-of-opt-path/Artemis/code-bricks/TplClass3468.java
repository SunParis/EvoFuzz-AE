public class TplClass3468 {

    private static final void method(byte[] sB2, byte[] sB1, int k, int N) throws Throwable {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sB1[k] = (byte) i;
                sB2[k] = (byte) j;
                k++;
            }
        }
    }
}

