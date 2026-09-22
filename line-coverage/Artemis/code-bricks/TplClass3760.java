public class TplClass3760 {

    private static final void method(int[] a, int sum, int n) throws Throwable {
        for (int i = 0; i < n; ) {
            int value = a[i] + 1;
            sum += value;
            ++i;
            a[i] = value;
        }
    }
}

