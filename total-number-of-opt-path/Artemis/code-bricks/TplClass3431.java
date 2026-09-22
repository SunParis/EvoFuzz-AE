public class TplClass3431 {

    private static final void method(short[] b2, int min_length, short[] bo, short[] b1) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            // Cancelling constant computations do not confuse recognition.
            bo[i] = (short) (((b1[i] + 10) + (b2[i] - 10)) >> 1);
        }
    }
}

