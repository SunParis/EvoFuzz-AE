public class TplClass3426 {

    private static final void method(int result, int i, int[] array) throws Throwable {
        result += array[i];
        for (int j = 0; j < 2; ++j) {
            // The HBoundsCheck for this array access will be updated to access
            // the array length phi created for the deoptimization checks of the
            // first loop. This crashed the compiler which used to DCHECK an array
            // length in a bounds check cannot be a phi.
            result += array[j];
        }
    }
}

