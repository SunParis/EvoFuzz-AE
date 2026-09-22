public class TplClass3527 {

    private static final void method(char[] sB2, char[] sB1, int M, char[] interesting) throws Throwable {
        // set up some extra values to exercise the cleanup loop.
        for (int i = 0; i < M; i++) {
            sB1[i] = (char) i;
            sB2[i] = interesting[i & 7];
        }
    }
}

