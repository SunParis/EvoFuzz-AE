public class TplClass2035 {

    private static final void method(double[] a0) throws Throwable {
        int i = 0;
        for (; i < a0.length - 4; i += 4) {
            a0[i + 0] = 0.;
            a0[i + 1] = 1.;
            a0[i + 2] = 2.;
            a0[i + 3] = 3.;
        }
        for (; i < a0.length; i++) {
            a0[i] = (double) (i & 3);
        }
    }
}

