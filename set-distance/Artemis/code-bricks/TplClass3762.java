public class TplClass3762 {

    private static final void method(int[] a, int sum, int n) throws Throwable {
        for (int i = 0; i < n; ) {
            int value = a[i];
            sum += value;
            ++i;
            a[i] = value;
        }
    }
}

