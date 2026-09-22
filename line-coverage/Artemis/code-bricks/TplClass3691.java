public class TplClass3691 {

    private static final void method(double[] a, double[] b) throws Throwable {
        b[20] = 99;
        for (int i = 0; i < a.length; i++) {
            a[i] = b[20] - 7;
        }
    }
}

