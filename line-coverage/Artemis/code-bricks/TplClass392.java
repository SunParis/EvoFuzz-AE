public class TplClass392 {

    private static final void method(int paddedSize) throws Throwable {
        byte[] padded = new byte[paddedSize];
        int psSize = padded.length;
        int k = psSize - 1;
        for (int i = 0; i < psSize; i++) {
            padded[k--] = (byte) 0xff;
        }
    }
}

