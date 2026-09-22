public class TplClass4252 {

    private static final void method(int[] a, int lo, int sResult) throws Throwable {
        for (int i = lo; i <= 10; i++) {
            // Dangerous loop where careless static range analysis would yield strict upper bound
            // on index j of 5. When, for instance, lo and thus i = -2147483648, the upper bound
            // becomes really positive due to arithmetic wrap-around, causing OOB.
            for (int j = 4; j < i - 5; j++) {
                sResult += a[j - 4];
            }
        }
    }
}

