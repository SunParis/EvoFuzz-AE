public class TplClass1693 {

    private static final void method(char[] a1, int VALUE, int BIT_MASK, char[] a0) throws Throwable {
        for (int i = 0; i < a0.length; i += 1) {
            a0[i] = (char) ((a1[i] & BIT_MASK) >>> VALUE);
        }
    }
}

