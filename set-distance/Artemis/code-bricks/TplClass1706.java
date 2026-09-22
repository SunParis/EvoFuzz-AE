public class TplClass1706 {

    private static final void method(char[] a1, int[] p2) throws Throwable {
        for (int i = 0; i < p2.length; i += 1) {
            int l0 = (int) a1[i * 2 + 0];
            int l1 = (int) a1[i * 2 + 1];
            p2[i] = (l0 << 16) | (l1 & 0xFFFF);
        }
    }
}

