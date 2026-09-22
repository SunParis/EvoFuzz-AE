public class TplClass1417 {

    private static final void method(byte[] a1, short[] p2) throws Throwable {
        for (int i = 0; i < p2.length; i += 1) {
            short l0 = (short) a1[i * 2 + 0];
            short l1 = (short) a1[i * 2 + 1];
            p2[i] = (short) ((l0 << 8) | (l1 & 0xFF));
        }
    }
}

