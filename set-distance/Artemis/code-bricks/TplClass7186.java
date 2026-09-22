public class TplClass7186 {

    private static final void method(int length, int[] a, int hold) throws Throwable {
        for (int pass = 1; pass < length; pass++) {
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
            // End of i loop
        }
    }
}

