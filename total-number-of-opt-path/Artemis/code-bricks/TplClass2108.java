public class TplClass2108 {

    private static final void method(short[] a1, int i, short[] a0) throws Throwable {
        for (; i < a0.length; i++) {
            a0[i] = (short) (a1[i] + (i & 3));
        }
    }
}

