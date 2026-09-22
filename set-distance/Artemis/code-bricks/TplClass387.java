public class TplClass387 {

    private static final void method(byte[] padded) throws Throwable {
        int psSize = padded.length;
        int k = 0;
        while (psSize-- > 0) {
            padded[k++] = (byte) 0xff;
        }
    }
}

