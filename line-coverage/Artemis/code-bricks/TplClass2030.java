public class TplClass2030 {

    private static final void method(double[] a1, int i, double[] a0) throws Throwable {
        for (; i < a0.length; i++) {
            a0[i] = a1[i] / (double) ((i & 3) + 1);
        }
    }
}

