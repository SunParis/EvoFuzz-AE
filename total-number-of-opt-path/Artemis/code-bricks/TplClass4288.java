public class TplClass4288 {

    private static final void method(int[] a, int j) throws Throwable {
        if (a[j] > a[j + 1]) {
            int tmp = a[j];
            a[j] = a[j + 1];
            a[j + 1] = tmp;
        }
    }
}

