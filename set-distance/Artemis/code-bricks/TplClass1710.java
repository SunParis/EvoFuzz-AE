public class TplClass1710 {

    private static final void method(char[] a1, long[] p4) throws Throwable {
        for (int i = 0; i < p4.length; i += 1) {
            long l0 = (long) a1[i * 4 + 0];
            long l1 = (long) a1[i * 4 + 1];
            long l2 = (long) a1[i * 4 + 2];
            long l3 = (long) a1[i * 4 + 3];
            p4[i] = (l3 & 0xFFFFl) | ((l2 & 0xFFFFl) << 16) | ((l1 & 0xFFFFl) << 32) | ((l0 & 0xFFFFl) << 48);
        }
    }
}

