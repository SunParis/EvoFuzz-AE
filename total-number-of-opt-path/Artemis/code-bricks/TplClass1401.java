public class TplClass1401 {

    private static final void method(byte[] a1, int b, byte[] a0) throws Throwable {
        for (int i = 0; i < a0.length; i += 1) {
            a0[i] = (byte) (a1[i] >>> b);
        }
    }
}

