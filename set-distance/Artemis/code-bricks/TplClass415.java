public class TplClass415 {

    private static final void method(int start, float[] a, int end, boolean error, float value) throws Throwable {
        for (int j = start; j < end; j++) {
            if (a[j] != value) {
                error = true;
            }
        }
    }
}

