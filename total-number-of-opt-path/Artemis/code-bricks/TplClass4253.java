public class TplClass4253 {

    private static final void method(int[] a, int i, int sResult) throws Throwable {
        // becomes really positive due to arithmetic wrap-around, causing OOB.
        for (int j = 4; j < i - 5; j++) {
            sResult += a[j - 4];
        }
    }
}

