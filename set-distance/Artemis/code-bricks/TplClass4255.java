public class TplClass4255 {

    private static final void method(int[] a, int i, int sResult) throws Throwable {
        // becomes really negative due to arithmetic wrap-around, causing OOB.
        for (int j = 6; j > i + 5; j--) {
            sResult += a[j - 6];
        }
    }
}

