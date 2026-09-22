public class TplClass389 {

    private static final void method(int paddedSize) throws Throwable {
        byte[] padded = new byte[paddedSize];
        int psSize = padded.length;
        int k = 0;
        while (psSize-- > 0) {
            padded[k++] = (byte) 0xff;
        }
    }
}

