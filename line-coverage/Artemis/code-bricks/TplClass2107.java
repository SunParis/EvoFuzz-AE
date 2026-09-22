public class TplClass2107 {

    private static final void method(short[] a1, int i, short[] a0) throws Throwable {
        for (; i < a0.length - 4; i += 4) {
            a0[i + 0] = (short) (a1[i + 0] + 0);
            a0[i + 1] = (short) (a1[i + 1] + 1);
            a0[i + 2] = (short) (a1[i + 2] + 2);
            a0[i + 3] = (short) (a1[i + 3] + 3);
        }
    }
}

