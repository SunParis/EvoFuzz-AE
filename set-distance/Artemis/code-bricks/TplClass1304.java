public class TplClass1304 {

    private static final void method(int[] a1, int[] a2, int[] a3, int[] a4, int BIT_MASK, int ADD_INIT, int i, int gold_sum, int VALUE) throws Throwable {
        int val = (int) (ADD_INIT + i);
        gold_sum += val;
        a1[i] = val;
        a2[i] = (int) VALUE;
        a3[i] = (int) -VALUE;
        a4[i] = (int) BIT_MASK;
    }
}

