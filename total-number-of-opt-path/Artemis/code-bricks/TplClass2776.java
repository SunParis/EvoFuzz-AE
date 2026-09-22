public class TplClass2776 {

    private static final void method(int result, int[] x, int k) throws Throwable {
        // arithmetic while computing the trip-count for this very large stride.
        for (int i = 1; i < Integer.MAX_VALUE; i += 195225786) {
            result += x[k++];
        }
    }
}

