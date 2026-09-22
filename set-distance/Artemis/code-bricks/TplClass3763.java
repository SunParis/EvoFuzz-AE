public class TplClass3763 {

    private static final void method(int zero, int[] a, int i, int n) throws Throwable {
        for (; i < n; ++i) {
            a[i] = i;
            // Extra instructions to avoid loop unrolling.
            zero = (((zero ^ 1) + 2) ^ 1) - 2;
            zero = (((zero ^ 4) + 8) ^ 4) - 8;
        }
    }
}

