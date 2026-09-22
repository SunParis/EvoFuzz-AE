public class TplClass4256 {

    private static final void method(int[] a, int hi, int sResult) throws Throwable {
        for (int i = -1; i <= hi; i++) {
            // Dangerous loop where careless static range analysis would yield strict lower bound
            // on index j of 0. For large i, the initial value of j becomes really negative due
            // to arithmetic wrap-around, causing OOB.
            for (int j = i + 1; j < 1; j++) {
                sResult += a[j];
            }
        }
    }
}

