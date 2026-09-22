public class TplClass1416 {

    private static final void method(short[] p2, byte[] a0) throws Throwable {
        for (int i = 0; i < p2.length; i += 1) {
            short l = p2[i];
            a0[i * 2 + 0] = (byte) (l & 0xFF);
            a0[i * 2 + 1] = (byte) (l >> 8);
        }
    }
}

