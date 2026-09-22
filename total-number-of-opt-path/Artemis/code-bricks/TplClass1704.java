public class TplClass1704 {

    private static final void method(char[] a1, int[] p2) throws Throwable {
        for (int i = 0; i < p2.length; i += 1) {
            int l0 = (int) a1[i * 2 + 0];
            int l1 = (int) a1[i * 2 + 1];
            p2[i] = (l1 << 16) | (l0 & 0xFFFF);
        }
    }
}

