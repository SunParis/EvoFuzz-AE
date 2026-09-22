public class TplClass2024 {

    private static final void method(double[] a1, double[] a0) throws Throwable {
        for (int i = 0; i < a0.length; i += 1) {
            a0[i] = a1[i] / (double) ((i & 3) + 1);
        }
    }
}

