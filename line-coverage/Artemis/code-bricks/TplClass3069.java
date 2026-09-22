public class TplClass3069 {

    private static final void method(byte[] b2, int min_length, byte sad, byte[] b1) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            byte s = b1[i];
            byte p = b2[i];
            int x = s - p;
            if (x < 0)
                x = -x;
            sad += x;
        }
    }
}

