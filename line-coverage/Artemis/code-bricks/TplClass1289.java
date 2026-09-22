public class TplClass1289 {

    private static final void method(int[] a1, int b, int VALUE, int[] a0) throws Throwable {
        for (int i = 0; i < a0.length; i += 1) {
            a0[i] = (int) ((a1[i] & b) >>> VALUE);
        }
    }
}

