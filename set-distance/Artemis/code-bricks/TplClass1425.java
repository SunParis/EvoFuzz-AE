public class TplClass1425 {

    private static final void method(byte[] a1, long[] p8) throws Throwable {
        for (int i = 0; i < p8.length; i += 1) {
            long l0 = (long) a1[i * 8 + 0];
            long l1 = (long) a1[i * 8 + 1];
            long l2 = (long) a1[i * 8 + 2];
            long l3 = (long) a1[i * 8 + 3];
            long l4 = (long) a1[i * 8 + 4];
            long l5 = (long) a1[i * 8 + 5];
            long l6 = (long) a1[i * 8 + 6];
            long l7 = (long) a1[i * 8 + 7];
            p8[i] = (l7 & 0xFFl) | ((l6 & 0xFFl) << 8) | ((l5 & 0xFFl) << 16) | ((l4 & 0xFFl) << 24) | ((l3 & 0xFFl) << 32) | ((l2 & 0xFFl) << 40) | ((l1 & 0xFFl) << 48) | ((l0 & 0xFFl) << 56);
        }
    }
}

