public class TplClass1484 {

    private static final void method(byte[] a1, int i, int[] p4) throws Throwable {
        int l0 = (int) a1[i * 4 + 0];
        int l1 = (int) a1[i * 4 + 1];
        int l2 = (int) a1[i * 4 + 2];
        int l3 = (int) a1[i * 4 + 3];
        p4[i] = (l0 & 0xFF) | ((l1 & 0xFF) << 8) | ((l2 & 0xFF) << 16) | ((l3 & 0xFF) << 24);
    }
}

