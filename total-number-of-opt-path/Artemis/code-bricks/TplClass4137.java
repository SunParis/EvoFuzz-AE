public class TplClass4137 {

    private static final void method(int[] a, int i, int[] b) throws Throwable {
        int t = b[i - 2] + b[i] + b[i + 2] + (((i & 1) == 0) ? b[i + 1] : b[i - 1]);
        a[i] = t;
    }
}

