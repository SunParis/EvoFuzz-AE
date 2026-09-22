public class TplClass4314 {

    private static final void method(int hi, int sResult) throws Throwable {
        int[] a = { 11 };
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

