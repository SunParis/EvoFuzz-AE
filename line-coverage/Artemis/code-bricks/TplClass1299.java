public class TplClass1299 {

    private static final void method(int[] a1, long[] p2) throws Throwable {
        for (int i = 0; i < p2.length; i += 1) {
            long l0 = (long) a1[i * 2 + 0];
            long l1 = (long) a1[i * 2 + 1];
            p2[i] = (l1 << 32) | (l0 & 0xFFFFFFFFl);
        }
    }
}

