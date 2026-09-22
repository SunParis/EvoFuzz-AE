public class TplClass4415 {

    private static final void method(int[] a, int i) throws Throwable {
        if (a[i] < a[i + 1]) {
            int tmp = a[i];
            a[i] = a[i + 1];
            a[i + 1] = tmp;
        }
    }
}

