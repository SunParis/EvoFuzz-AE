public class TplClass1609 {

    private static final void method(int i, int[] p2, short[] a0) throws Throwable {
        int l = p2[i];
        a0[i * 2 + 0] = (short) (l >> 16);
        a0[i * 2 + 1] = (short) (l & 0xFFFF);
    }
}

