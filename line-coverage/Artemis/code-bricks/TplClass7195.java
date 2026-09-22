public class TplClass7195 {

    private static final void method(int[] a, int i, int hold) throws Throwable {
        // a single pass
        if (a[i] > a[i + 1]) {
            // then swap
            hold = a[i];
            a[i] = a[i + 1];
            a[i + 1] = hold;
        }
    }
}

