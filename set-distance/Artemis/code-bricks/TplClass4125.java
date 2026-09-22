public class TplClass4125 {

    private static final void method(int[] a, int[] b) throws Throwable {
        // TODO: range information could remove another
        for (int i = 1; i < a.length - 1; i++) {
            a[i] = b[i - 1] + b[i] + b[i + 1];
        }
    }
}

