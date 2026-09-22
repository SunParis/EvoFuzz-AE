public class TplClass2766 {

    private static final void method(int result, int[] a) throws Throwable {
        for (int i = 1; i <= 1; i++) {
            // Obscured unit stride.
            for (int j = 0; j < a.length; j += i) {
                result += a[j];
            }
        }
    }
}

