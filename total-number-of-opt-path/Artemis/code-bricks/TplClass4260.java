public class TplClass4260 {

    private static final void method(int[] a, int sResult) throws Throwable {
        for (int i = -1; i <= 0; i++) {
            // Dangerous loop similar as above where the loop is now finite, but the
            // loop still goes out of bounds for i = -1 due to the large upper bound.
            for (int j = -4; j < 2147483646 * i - 3; j++) {
                sResult += a[j + 4];
            }
        }
    }
}

