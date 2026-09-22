public class TplClass4423 {

    private static final void method(int[] a, int[] b) throws Throwable {
        b[20] = 99;
        for (int i = 0; i < a.length; i++) {
            a[i] = b[20] - 7;
            i++;
            a[i] = b[20] - 7;
        }
    }
}

