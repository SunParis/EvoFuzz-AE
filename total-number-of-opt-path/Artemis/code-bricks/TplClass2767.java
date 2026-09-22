public class TplClass2767 {

    private static final void method(int result, int[] a, int i) throws Throwable {
        // Obscured unit stride.
        for (int j = 0; j < a.length; j += i) {
            result += a[j];
        }
    }
}

