public class TplClass1301 {

    private static final void method(int[] a1, long[] p2) throws Throwable {
        for (int i = 0; i < p2.length; i += 1) {
            long l0 = (long) a1[i * 2 + 0];
            long l1 = (long) a1[i * 2 + 1];
            p2[i] = (l0 << 32) | (l1 & 0xFFFFFFFFl);
        }
    }
}

