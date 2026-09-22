public class TplClass3420 {

    private static final void method(int result, int start, int[] array) throws Throwable {
        // for the array length which will only be used within the loop.
        for (int i = start; i < 3; i++) {
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
}

