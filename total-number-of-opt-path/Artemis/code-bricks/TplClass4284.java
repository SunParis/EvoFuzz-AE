public class TplClass4284 {

    private static final void method(int result, int[] q, int hi, java.lang.Integer[] z, int lo) throws Throwable {
        for (int i = lo; i < hi; i++) {
            // Similar to above, but now implicit call to intValue() may prevent hoisting
            // z[0] itself during BCE on q[i]. Therefore, we just check BCE on q[i].
            result += q[i] + z[0];
        }
    }
}

