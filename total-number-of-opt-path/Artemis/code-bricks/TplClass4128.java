public class TplClass4128 {

    private static final void method(int start, int end, int[] array) throws Throwable {
        // Real-life example: should have four deopts: one null and three bounds.
        for (int i = end; i >= start; i--) {
            array[i] = (array[i - 2] + array[i - 1] + array[i] + array[i + 1] + array[i + 2]) / 5;
        }
    }
}

