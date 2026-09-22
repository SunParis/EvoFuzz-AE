public class TplClass2023 {

    private static final void method(double[] a1, double[] a0) throws Throwable {
        for (int i = 0; i < a0.length; i += 1) {
            a0[i] = a1[i] + (double) (i & 3);
        }
    }
}

