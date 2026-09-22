public class TplClass925 {

    private static final void method(float[] a1, float VALUE, int SFP_ITERS, float[] a0) throws Throwable {
        // Non-counted loop with safepoint.
        for (long l = 0; l < SFP_ITERS; l++) {
            // Counted and vectorized loop.
            for (int i = 0; i < a0.length; i += 1) {
                a0[i] += a1[i] + VALUE;
            }
        }
    }
}

