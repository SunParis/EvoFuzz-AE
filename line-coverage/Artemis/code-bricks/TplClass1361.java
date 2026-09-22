public class TplClass1361 {

    private static final void method(int i, long[] p2, int[] a0) throws Throwable {
        long l = p2[i];
        a0[i * 2 + 0] = (int) (l >> 32);
        a0[i * 2 + 1] = (int) (l & 0xFFFFFFFFl);
    }
}

