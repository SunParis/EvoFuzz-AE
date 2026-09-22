public class TplClass7185 {

    private static final void method(int missing, int length, int[] a, int expected, int element) throws Throwable {
        for (int i = 0; i < length; i++) {
            while ((a[i] != element) & (element < expected)) {
                if (missing % 20 == 19) {
                }
                missing++;
                element++;
            }
            element++;
        }
    }
}

