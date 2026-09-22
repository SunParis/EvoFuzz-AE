public class TplClass3440 {

    private static final void method(short[] sB2, short[] sB1, int M, short[] interesting) throws Throwable {
        // set up some extra values to exercise the cleanup loop.
        for (int i = 0; i < M; i++) {
            sB1[i] = (short) i;
            sB2[i] = interesting[i & 7];
        }
    }
}

