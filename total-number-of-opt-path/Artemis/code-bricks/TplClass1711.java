public class TplClass1711 {

    private static final void method(long[] p4, char[] a0) throws Throwable {
        for (int i = 0; i < p4.length; i += 1) {
            long l = p4[i];
            a0[i * 4 + 0] = (char) (l >> 48);
            a0[i * 4 + 1] = (char) (l >> 32);
            a0[i * 4 + 2] = (char) (l >> 16);
            a0[i * 4 + 3] = (char) (l & 0xFFFFl);
        }
    }
}

