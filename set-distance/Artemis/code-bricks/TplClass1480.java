public class TplClass1480 {

    private static final void method(byte[] a1, int i, short[] p2) throws Throwable {
        short l0 = (short) a1[i * 2 + 0];
        short l1 = (short) a1[i * 2 + 1];
        p2[i] = (short) ((l1 << 8) | (l0 & 0xFF));
    }
}

