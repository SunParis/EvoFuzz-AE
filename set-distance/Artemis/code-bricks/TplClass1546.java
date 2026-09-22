public class TplClass1546 {

    private static final void method(int[] p2, short[] a0) throws Throwable {
        for (int i = 0; i < p2.length; i += 1) {
            int l = p2[i];
            a0[i * 2 + 0] = (short) (l & 0xFFFF);
            a0[i * 2 + 1] = (short) (l >> 16);
        }
    }
}

