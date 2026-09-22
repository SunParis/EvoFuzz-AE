public class TplClass390 {

    private static final void method(int paddedSize) throws Throwable {
        byte[] padded = new byte[paddedSize];
        int psSize = padded.length;
        for (int k = 0; psSize > 0; psSize--) {
            int i = padded.length - psSize;
            padded[i] = (byte) 0xff;
        }
    }
}

