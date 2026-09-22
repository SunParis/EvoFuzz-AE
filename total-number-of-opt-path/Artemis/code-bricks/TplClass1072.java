public class TplClass1072 {

    private static final void method(float[] a1, int ARRLEN, float[] a2, float[] a3, float ADD_INIT, float gold_sum, float VALUE) throws Throwable {
        for (int i = 0; i < ARRLEN; i++) {
            float val = ADD_INIT + (float) i;
            gold_sum += val;
            a1[i] = val;
            a2[i] = VALUE;
            a3[i] = -VALUE;
        }
    }
}

