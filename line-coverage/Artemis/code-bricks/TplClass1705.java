public class TplClass1705 {

    private static final void method(int[] p2, char[] a0) throws Throwable {
        for (int i = 0; i < p2.length; i += 1) {
            int l = p2[i];
            a0[i * 2 + 0] = (char) (l & 0xFFFF);
            a0[i * 2 + 1] = (char) (l >> 16);
        }
    }
}

