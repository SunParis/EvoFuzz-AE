public class TplClass3575 {

    private static final void method(short[] a, short[] b, short[] out) throws Throwable {
        int min_length = Math.min(out.length, Math.min(a.length, b.length));
        for (int i = 0; i < min_length; i++) {
            out[i] = (short) (((short) (a[i] + b[i])) >> 1);
        }
    }
}

