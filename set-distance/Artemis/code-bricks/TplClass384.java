public class TplClass384 {

    private static final void method(byte[] padded, int psSize) throws Throwable {
        for (int k = 0; psSize > 0; psSize--) {
            int i = padded.length - psSize;
            padded[i] = (byte) 0xff;
        }
    }
}

