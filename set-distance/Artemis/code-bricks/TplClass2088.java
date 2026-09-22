public class TplClass2088 {

    private static final void method(byte[] a1, int i, byte[] a0) throws Throwable {
        for (; i < a0.length; i++) {
            a0[i] = (byte) (a1[i] + (i & 3));
        }
    }
}

