public class TplClass385 {

    private static final void method(int k, byte[] padded, int psSize) throws Throwable {
        for (int i = 0; i < psSize; i++) {
            padded[k--] = (byte) 0xff;
        }
    }
}

