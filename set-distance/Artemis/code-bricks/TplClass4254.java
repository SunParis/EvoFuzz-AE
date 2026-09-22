public class TplClass4254 {

    private static final void method(int[] a, int hi, int sResult) throws Throwable {
        for (int i = 0; i < hi; i++) {
            // Dangerous loop where careless static range analysis would yield strict lower bound
            // on index j of 5. When, for instance, hi and thus i = 2147483647, the upper bound
            // becomes really negative due to arithmetic wrap-around, causing OOB.
            for (int j = 6; j > i + 5; j--) {
                sResult += a[j - 6];
            }
        }
    }
}

