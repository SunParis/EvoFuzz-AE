public class TplClass4257 {

    private static final void method(int[] a, int i, int sResult) throws Throwable {
        // to arithmetic wrap-around, causing OOB.
        for (int j = i + 1; j < 1; j++) {
            sResult += a[j];
        }
    }
}

