public class TplClass4316 {

    private static final void method(int sResult) throws Throwable {
        int[] a = { 11 };
        for (int i = -1; i <= 0; i++) {
            // Dangerous loop where careless static range analysis would yield a safe upper bound
            // of -3. In reality, due to arithmetic wrap-around (when i = -1, j <= 2147483647;
            // whereas when i = 0, j <= -3), this is an infinite loop that goes OOB.
            for (int j = -3; j <= 2147483646 * i - 3; j++) {
                sResult += a[j + 3];
            }
        }
    }
}

