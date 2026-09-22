public class TplClass2059 {

    private static final void method(long[] a1, long[] a0) throws Throwable {
        int i = 0;
        for (; i < a0.length - 4; i += 4) {
            a0[i + 0] = (long) (a1[i + 0] << 0);
            a0[i + 1] = (long) (a1[i + 1] << 1);
            a0[i + 2] = (long) (a1[i + 2] << 2);
            a0[i + 3] = (long) (a1[i + 3] << 3);
        }
        for (; i < a0.length; i++) {
            a0[i] = (long) (a1[i] << (i & 3));
        }
    }
}

