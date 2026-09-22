public class TplClass2117 {

    private static final void method(short[] a1, short[] a0) throws Throwable {
        int i = 0;
        for (; i < a0.length - 4; i += 4) {
            a0[i + 0] = (short) (a1[i + 0] + 0);
            a0[i + 1] = (short) (a1[i + 1] + 1);
            a0[i + 2] = (short) (a1[i + 2] + 2);
            a0[i + 3] = (short) (a1[i + 3] + 3);
        }
        for (; i < a0.length; i++) {
            a0[i] = (short) (a1[i] + (i & 3));
        }
    }
}

