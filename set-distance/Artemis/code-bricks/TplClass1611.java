public class TplClass1611 {

    private static final void method(int i, long[] p4, short[] a0) throws Throwable {
        long l = p4[i];
        a0[i * 4 + 0] = (short) (l & 0xFFFFl);
        a0[i * 4 + 1] = (short) (l >> 16);
        a0[i * 4 + 2] = (short) (l >> 32);
        a0[i * 4 + 3] = (short) (l >> 48);
    }
}

