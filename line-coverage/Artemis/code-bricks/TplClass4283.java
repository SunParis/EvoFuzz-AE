public class TplClass4283 {

    private static final void method(int hi, int lo, int result, int[] q, boolean[] r, byte[] s, char[] t, short[] u, int[] v, long[] w, float[] x, double[] y) throws Throwable {
        for (int i = lo; i < hi; i++) {
            // All constant index array references can be hoisted out of the loop during BCE on q[i].
            result += q[i] + (r[0] ? 1 : 0) + (int) s[0] + (int) t[0] + (int) u[0] + (int) v[0] + (int) w[0] + (int) x[0] + (int) y[0];
        }
    }
}

