public class TplClass4231 {

    private static final void method(int[] a) throws Throwable {
        for (int i = a.length; --i >= 0; ) {
            for (int j = 0; j < i; j++) {
                if (a[j] > a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                }
            }
        }
    }
}

