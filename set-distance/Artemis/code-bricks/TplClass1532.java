public class TplClass1532 {

    private static final void method(short[] a1, int VALUE, int ADD_INIT, short[] a0) throws Throwable {
        for (int i = 0; i < a0.length; i += 1) {
            a0[i] = (short) ((a1[i] + ADD_INIT) >>> VALUE);
        }
    }
}

