public class TplClass1766 {

    private static final void method(int i, int[] p2, char[] a0) throws Throwable {
        int l = p2[i];
        a0[i * 2 + 0] = (char) (l & 0xFFFF);
        a0[i * 2 + 1] = (char) (l >> 16);
    }
}

