public class TplClass7199 {

    private static final void method(int missing, int[] a, int i, int expected, int element) throws Throwable {
        while ((a[i] != element) & (element < expected)) {
            if (missing % 20 == 19) {
            }
            missing++;
            element++;
        }
        element++;
    }
}

