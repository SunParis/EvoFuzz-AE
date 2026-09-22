public class TplClass1411 {

    private static final void method(byte[] a1, int VALUE, int ADD_INIT, byte[] a0) throws Throwable {
        for (int i = 0; i < a0.length; i += 1) {
            a0[i] = (byte) ((a1[i] + ADD_INIT) >> VALUE);
        }
    }
}

