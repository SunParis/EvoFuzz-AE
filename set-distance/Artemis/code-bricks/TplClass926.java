public class TplClass926 {

    private static final void method(float[] a1, float VALUE, float[] a0) throws Throwable {
        // Counted and vectorized loop.
        for (int i = 0; i < a0.length; i += 1) {
            a0[i] += a1[i] + VALUE;
        }
    }
}

