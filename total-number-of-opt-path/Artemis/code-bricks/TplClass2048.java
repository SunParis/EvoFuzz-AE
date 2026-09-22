public class TplClass2048 {

    private static final void method(long[] a1, int i, long[] a0) throws Throwable {
        for (; i < a0.length; i++) {
            a0[i] = (long) (a1[i] + (i & 3));
        }
    }
}

