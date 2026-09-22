public class TplClass2043 {

    private static final void method(long[] a1, long[] a0) throws Throwable {
        for (int i = 0; i < a0.length; i += 1) {
            a0[i] = (long) (a1[i] + (i & 3));
        }
    }
}

