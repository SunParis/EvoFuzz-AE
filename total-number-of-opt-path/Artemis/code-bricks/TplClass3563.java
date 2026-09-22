public class TplClass3563 {

    private static final void method(short[] a, short[] b, int min_length, short[] out) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            out[i] = (short) (((short) (a[i] + b[i])) >> 1);
        }
    }
}

