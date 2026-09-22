public class TplClass1427 {

    private static final void method(byte[] a1, byte[] a2, byte[] a3, byte[] a4, int BIT_MASK, int ADD_INIT, int i, int gold_sum, int VALUE) throws Throwable {
        byte val = (byte) (ADD_INIT + i);
        gold_sum += val;
        a1[i] = val;
        a2[i] = (byte) VALUE;
        a3[i] = (byte) -VALUE;
        a4[i] = (byte) BIT_MASK;
    }
}

