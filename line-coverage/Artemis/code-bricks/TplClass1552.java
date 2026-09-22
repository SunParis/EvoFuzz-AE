public class TplClass1552 {

    private static final void method(long[] p4, short[] a0) throws Throwable {
        for (int i = 0; i < p4.length; i += 1) {
            long l = p4[i];
            a0[i * 4 + 0] = (short) (l >> 48);
            a0[i * 4 + 1] = (short) (l >> 32);
            a0[i * 4 + 2] = (short) (l >> 16);
            a0[i * 4 + 3] = (short) (l & 0xFFFFl);
        }
    }
}

