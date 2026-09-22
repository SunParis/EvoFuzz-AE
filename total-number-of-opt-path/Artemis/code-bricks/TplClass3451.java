public class TplClass3451 {

    private static final void method(short[] b2, short[] bo, short[] b1) throws Throwable {
        int min_length = Math.min(bo.length, Math.min(b1.length, b2.length));
        for (int i = 0; i < min_length; i++) {
            // Computations that cancel to adding 1 also do not confuse recognition.
            bo[i] = (short) (((b1[i] + 10) + (b2[i] - 9)) >> 1);
        }
    }
}

