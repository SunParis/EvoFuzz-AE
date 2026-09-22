public class TplClass1712 {

    private static final void method(char[] a1, short[] a2, short[] a3, short[] a4, int BIT_MASK, int ADD_INIT, int i, int gold_sum, int VALUE) throws Throwable {
        char val = (char) (ADD_INIT + i);
        gold_sum += val;
        a1[i] = val;
        a2[i] = (short) VALUE;
        a3[i] = (short) -VALUE;
        a4[i] = (short) BIT_MASK;
    }
}

