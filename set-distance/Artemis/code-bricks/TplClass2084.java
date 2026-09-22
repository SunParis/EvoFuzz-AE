public class TplClass2084 {

    private static final void method(byte[] a1, byte[] a0) throws Throwable {
        for (int i = 0; i < a0.length; i += 1) {
            a0[i] = (byte) (a1[i] << (i & 3));
        }
    }
}

