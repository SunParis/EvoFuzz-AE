public class TplClass4126 {

    private static final void method(int[] a, int[] b) throws Throwable {
        // One redundant deopt is removed by simplifier.
        for (int i = 2; i < a.length - 2; i++) {
            int t = b[i - 2] + b[i] + b[i + 2] + (((i & 1) == 0) ? b[i + 1] : b[i - 1]);
            a[i] = t;
        }
    }
}

