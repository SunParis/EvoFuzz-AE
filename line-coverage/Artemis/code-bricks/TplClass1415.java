public class TplClass1415 {

    private static final void method(byte[] a1, short[] p2) throws Throwable {
        for (int i = 0; i < p2.length; i += 1) {
            short l0 = (short) a1[i * 2 + 0];
            short l1 = (short) a1[i * 2 + 1];
            p2[i] = (short) ((l1 << 8) | (l0 & 0xFF));
        }
    }
}

