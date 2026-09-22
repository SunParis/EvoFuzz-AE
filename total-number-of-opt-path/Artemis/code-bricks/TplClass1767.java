public class TplClass1767 {

    private static final void method(char[] a1, int i, int[] p2) throws Throwable {
        int l0 = (int) a1[i * 2 + 0];
        int l1 = (int) a1[i * 2 + 1];
        p2[i] = (l0 << 16) | (l1 & 0xFFFF);
    }
}

