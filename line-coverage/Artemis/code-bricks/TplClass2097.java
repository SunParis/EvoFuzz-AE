public class TplClass2097 {

    private static final void method(byte[] a1, byte[] a0) throws Throwable {
        int i = 0;
        for (; i < a0.length - 4; i += 4) {
            a0[i + 0] = (byte) (a1[i + 0] + 0);
            a0[i + 1] = (byte) (a1[i + 1] + 1);
            a0[i + 2] = (byte) (a1[i + 2] + 2);
            a0[i + 3] = (byte) (a1[i + 3] + 3);
        }
        for (; i < a0.length; i++) {
            a0[i] = (byte) (a1[i] + (i & 3));
        }
    }
}

