public class TplClass3571 {

    private static final void method(int[] a, short[] out) throws Throwable {
        int min_length = Math.min(out.length, a.length);
        for (int i = 0; i < min_length; i++) {
            out[i] = (short) (a[i] >> 1);
        }
    }
}

