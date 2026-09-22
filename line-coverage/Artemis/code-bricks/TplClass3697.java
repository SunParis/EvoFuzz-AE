public class TplClass3697 {

    private static final void method(long[] a, double[] b) throws Throwable {
        b[20] = -99;
        for (int i = 0; i < a.length; i++) {
            a[i] = (long) b[20] - 7;
        }
    }
}

