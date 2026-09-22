public class TplClass3764 {

    private static final void method(int zero, int i, int[] b, int n) throws Throwable {
        for (; i < n; ++i) {
            b[i] = i;
            // Extra instructions to avoid loop unrolling.
            zero = (((zero ^ 1) + 2) ^ 1) - 2;
            zero = (((zero ^ 4) + 8) ^ 4) - 8;
        }
    }
}

