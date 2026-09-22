public class TplClass1089 {

    private static final void method(float[] a1, float[] a2, float[] a3, float ADD_INIT, int i, float gold_sum, float VALUE) throws Throwable {
        float val = ADD_INIT + (float) i;
        gold_sum += val;
        a1[i] = val;
        a2[i] = VALUE;
        a3[i] = -VALUE;
    }
}

