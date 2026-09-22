public class TplClass3444 {

    private static final void method(short[] b2, int i, short[] bo, short[] b1) throws Throwable {
        // Cancelling constant computations do not confuse recognition.
        bo[i] = (short) (((b1[i] + 10) + (b2[i] - 10)) >> 1);
    }
}

