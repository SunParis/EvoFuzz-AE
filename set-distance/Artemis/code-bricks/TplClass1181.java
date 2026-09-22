public class TplClass1181 {

    private static final void method(long[] a1, int VALUE, long BIT_MASK, long[] a0) throws Throwable {
        for (int i = 0; i < a0.length; i += 1) {
            a0[i] = (long) ((a1[i] & BIT_MASK) >>> VALUE);
        }
    }
}

