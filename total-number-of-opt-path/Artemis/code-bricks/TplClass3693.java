public class TplClass3693 {

    private static final void method(int[] a, float[] b) throws Throwable {
        b[20] = -99;
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) b[20] - 7;
        }
    }
}

