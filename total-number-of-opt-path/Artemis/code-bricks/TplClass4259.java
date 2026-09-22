public class TplClass4259 {

    private static final void method(int[] a, int i, int sResult) throws Throwable {
        // whereas when i = 0, j <= -3), this is an infinite loop that goes OOB.
        for (int j = -3; j <= 2147483646 * i - 3; j++) {
            sResult += a[j + 3];
        }
    }
}

