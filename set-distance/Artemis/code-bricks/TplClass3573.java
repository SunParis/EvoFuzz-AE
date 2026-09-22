public class TplClass3573 {

    private static final void method(int[] a, int[] out) throws Throwable {
        int min_length = Math.min(out.length, a.length);
        for (int i = 0; i < min_length; i++) {
            out[i] = a[i] >> 1;
        }
    }
}

