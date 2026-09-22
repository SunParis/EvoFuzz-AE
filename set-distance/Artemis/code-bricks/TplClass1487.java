public class TplClass1487 {

    private static final void method(int i, int[] p4, byte[] a0) throws Throwable {
        int l = p4[i];
        a0[i * 4 + 0] = (byte) (l >> 24);
        a0[i * 4 + 1] = (byte) (l >> 16);
        a0[i * 4 + 2] = (byte) (l >> 8);
        a0[i * 4 + 3] = (byte) (l & 0xFF);
    }
}

