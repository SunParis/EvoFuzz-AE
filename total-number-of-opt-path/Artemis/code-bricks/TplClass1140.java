public class TplClass1140 {

    private static final void method(long[] a1, int ARRLEN, long[] a2, long[] a3, long[] a4, long BIT_MASK, long ADD_INIT, long gold_sum, int VALUE) throws Throwable {
        for (int i = 0; i < ARRLEN; i++) {
            long val = (long) (ADD_INIT + i);
            gold_sum += val;
            a1[i] = val;
            a2[i] = (long) VALUE;
            a3[i] = (long) -VALUE;
            a4[i] = (long) BIT_MASK;
        }
    }
}

