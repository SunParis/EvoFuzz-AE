public class TplClass7187 {

    private static final void method(int length, int[] a, int pass, int hold) throws Throwable {
        // passes over the array
        for (int i = 0; i < length - pass; i++) {
            // a single pass
            if (a[i] > a[i + 1]) {
                // then swap
                hold = a[i];
                a[i] = a[i + 1];
                a[i + 1] = hold;
            }
        }
    }
}

