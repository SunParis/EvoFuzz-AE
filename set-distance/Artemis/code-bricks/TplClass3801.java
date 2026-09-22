public class TplClass3801 {

    private static final void method(int[] a, int i, int sum) throws Throwable {
        int value = a[i] + 1;
        sum += value;
        ++i;
        a[i] = value;
    }
}

