public class TplClass1297 {

    private static final void method(int[] a1, int VALUE, int BIT_MASK, int[] a0) throws Throwable {
        for (int i = 0; i < a0.length; i += 1) {
            a0[i] = (int) ((a1[i] & BIT_MASK) >> VALUE);
        }
    }
}

