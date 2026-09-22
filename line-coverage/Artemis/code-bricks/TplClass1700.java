public class TplClass1700 {

    private static final void method(char[] a1, int VALUE, int ADD_INIT, char[] a0) throws Throwable {
        for (int i = 0; i < a0.length; i += 1) {
            a0[i] = (char) ((a1[i] + ADD_INIT) >> VALUE);
        }
    }
}

