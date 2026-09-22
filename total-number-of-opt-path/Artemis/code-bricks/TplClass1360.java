public class TplClass1360 {

    private static final void method(int[] a1, int i, long[] p2) throws Throwable {
        long l0 = (long) a1[i * 2 + 0];
        long l1 = (long) a1[i * 2 + 1];
        p2[i] = (l0 << 32) | (l1 & 0xFFFFFFFFl);
    }
}

