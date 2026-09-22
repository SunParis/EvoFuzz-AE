public class TplClass1651 {

    private static final void method(char[] a1, int ARRLEN, short[] a2, short[] a3, short[] a4, int BIT_MASK, int ADD_INIT, int gold_sum, int VALUE) throws Throwable {
        for (int i = 0; i < ARRLEN; i++) {
            char val = (char) (ADD_INIT + i);
            gold_sum += val;
            a1[i] = val;
            a2[i] = (short) VALUE;
            a3[i] = (short) -VALUE;
            a4[i] = (short) BIT_MASK;
        }
    }
}

