public class TplClass421 {

    private static final void method(int start, byte[] a, int end, boolean error, byte value) throws Throwable {
        for (int j = start; j < end; j++) {
            if (a[j] != value) {
                error = true;
            }
        }
    }
}

