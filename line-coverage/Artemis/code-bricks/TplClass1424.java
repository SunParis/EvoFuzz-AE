public class TplClass1424 {

    private static final void method(long[] p8, byte[] a0) throws Throwable {
        for (int i = 0; i < p8.length; i += 1) {
            long l = p8[i];
            a0[i * 8 + 0] = (byte) (l & 0xFFl);
            a0[i * 8 + 1] = (byte) (l >> 8);
            a0[i * 8 + 2] = (byte) (l >> 16);
            a0[i * 8 + 3] = (byte) (l >> 24);
            a0[i * 8 + 4] = (byte) (l >> 32);
            a0[i * 8 + 5] = (byte) (l >> 40);
            a0[i * 8 + 6] = (byte) (l >> 48);
            a0[i * 8 + 7] = (byte) (l >> 56);
        }
    }
}

