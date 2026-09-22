public class TplClass1106 {

    private static final void method(double[] a1, int ARRLEN, double[] a2, double[] a3, double ADD_INIT, double gold_sum, double VALUE) throws Throwable {
        for (int i = 0; i < ARRLEN; i++) {
            double val = ADD_INIT + (double) i;
            gold_sum += val;
            a1[i] = val;
            a2[i] = VALUE;
            a3[i] = -VALUE;
        }
    }
}

