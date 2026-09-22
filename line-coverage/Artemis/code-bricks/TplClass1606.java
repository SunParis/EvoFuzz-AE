public class TplClass1606 {

    private static final void method(short[] a1, int i, int[] p2) throws Throwable {
        int l0 = (int) a1[i * 2 + 0];
        int l1 = (int) a1[i * 2 + 1];
        p2[i] = (l1 << 16) | (l0 & 0xFFFF);
    }
}

