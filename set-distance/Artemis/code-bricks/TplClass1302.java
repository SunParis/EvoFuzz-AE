public class TplClass1302 {

    private static final void method(long[] p2, int[] a0) throws Throwable {
        for (int i = 0; i < p2.length; i += 1) {
            long l = p2[i];
            a0[i * 2 + 0] = (int) (l >> 32);
            a0[i * 2 + 1] = (int) (l & 0xFFFFFFFFl);
        }
    }
}

