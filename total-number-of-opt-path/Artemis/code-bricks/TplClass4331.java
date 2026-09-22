public class TplClass4331 {

    private static final void method(int[][] a, int i) throws Throwable {
        // "above" the deoptimization "barrier" on the bounds.
        a[1][i] = 1;
        a[2][i] = 2;
        a[99][i] = 3;
    }
}

