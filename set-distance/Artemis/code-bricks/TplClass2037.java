public class TplClass2037 {

    private static final void method(double[] a1, double[] a0) throws Throwable {
        int i = 0;
        for (; i < a0.length - 4; i += 4) {
            a0[i + 0] = a1[i + 0] + 0.;
            a0[i + 1] = a1[i + 1] + 1.;
            a0[i + 2] = a1[i + 2] + 2.;
            a0[i + 3] = a1[i + 3] + 3.;
        }
        for (; i < a0.length; i++) {
            a0[i] = a1[i] + (double) (i & 3);
        }
    }
}

