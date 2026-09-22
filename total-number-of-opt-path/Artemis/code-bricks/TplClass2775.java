public class TplClass2775 {

    private static final void method(int result, int[] x, int k) throws Throwable {
        // reasonably large positive stride far away from upper bound.
        for (int i = 1; i <= 10 * 10000000 + 1; i += 10000000) {
            result += x[k++];
        }
    }
}

