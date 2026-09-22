public class TplClass3566 {

    private static final void method(short[] interesting, int[] iA, int[] iB, int M, short[] sA, short[] sB) throws Throwable {
        // set up some extra values to exercise the cleanup loop.
        for (int i = 0; i < M; i++) {
            sA[i] = (short) i;
            sB[i] = interesting[i & 7];
            iA[i] = i;
            iB[i] = interesting[i & 7];
        }
    }
}

