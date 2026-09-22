public class TplClass3577 {

    private static final void method(int[] a, int[] b, int[] out) throws Throwable {
        int min_length = Math.min(out.length, Math.min(a.length, b.length));
        for (int i = 0; i < min_length; i++) {
            out[i] = (short) ((short) (a[i] + b[i]) >> 1);
        }
    }
}

