public class TplClass1694 {

    private static final void method(char[] a1, int b, int VALUE, char[] a0) throws Throwable {
        for (int i = 0; i < a0.length; i += 1) {
            a0[i] = (char) ((a1[i] & b) >>> VALUE);
        }
    }
}

