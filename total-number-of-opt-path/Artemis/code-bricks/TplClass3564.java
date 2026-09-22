public class TplClass3564 {

    private static final void method(int[] a, int[] b, int min_length, int[] out) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            out[i] = (short) ((short) (a[i] + b[i]) >> 1);
        }
    }
}

