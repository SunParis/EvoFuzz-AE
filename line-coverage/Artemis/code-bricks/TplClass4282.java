public class TplClass4282 {

    private static final void method(int result, int[] x, int[][] a, int hi, int lo) throws Throwable {
        for (int i = lo; i < hi; i++) {
            result += x[i];
            if ((i % 10) != 0) {
                // None of the subscripts inside a conditional are removed by dynamic bce,
                // making them a candidate for deoptimization based on constant indices.
                // Compiler should ensure the array loads are not subsequently hoisted
                // "above" the deoptimization "barrier" on the bounds.
                a[1][i] = 1;
                a[2][i] = 2;
                a[99][i] = 3;
            }
        }
    }
}

