public class TplClass1420 {

    private static final void method(int[] p4, byte[] a0) throws Throwable {
        for (int i = 0; i < p4.length; i += 1) {
            int l = p4[i];
            a0[i * 4 + 0] = (byte) (l & 0xFF);
            a0[i * 4 + 1] = (byte) (l >> 8);
            a0[i * 4 + 2] = (byte) (l >> 16);
            a0[i * 4 + 3] = (byte) (l >> 24);
        }
    }
}

