public class TplClass4290 {

    private static final void method(int[][] a, int i) throws Throwable {
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

