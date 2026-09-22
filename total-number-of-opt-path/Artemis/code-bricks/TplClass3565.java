public class TplClass3565 {

    private static final void method(short[] a, short[] b, int min_length, short[] out) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            out[i] = (short) (((short) (a[i] + b[i]) + 1) >> 1);
        }
    }
}

