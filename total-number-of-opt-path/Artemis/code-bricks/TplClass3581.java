public class TplClass3581 {

    private static final void method(short[] interesting, int[] iA, int[] iB, int i, short[] sA, short[] sB) throws Throwable {
        sA[i] = (short) i;
        sB[i] = interesting[i & 7];
        iA[i] = i;
        iB[i] = interesting[i & 7];
    }
}

