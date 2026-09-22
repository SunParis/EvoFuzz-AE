public class TplClass4261 {

    private static final void method(int[] a, int i, int sResult) throws Throwable {
        // loop still goes out of bounds for i = -1 due to the large upper bound.
        for (int j = -4; j < 2147483646 * i - 3; j++) {
            sResult += a[j + 4];
        }
    }
}

