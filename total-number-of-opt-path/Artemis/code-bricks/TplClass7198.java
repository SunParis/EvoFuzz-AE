public class TplClass7198 {

    private static final void method(int length, int[] a, int expected) throws Throwable {
        int missing = 0;
        int element = 0;
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

