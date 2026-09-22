public class TplClass1772 {

    private static final void method(int i, long[] p4, char[] a0) throws Throwable {
        long l = p4[i];
        a0[i * 4 + 0] = (char) (l >> 48);
        a0[i * 4 + 1] = (char) (l >> 32);
        a0[i * 4 + 2] = (char) (l >> 16);
        a0[i * 4 + 3] = (char) (l & 0xFFFFl);
    }
}

