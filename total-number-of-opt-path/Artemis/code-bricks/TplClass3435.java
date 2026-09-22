public class TplClass3435 {

    private static final void method(short[] b2, int min_length, short[] bo, short[] b1) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            // Computations that cancel to adding 1 also do not confuse recognition.
            bo[i] = (short) (((b1[i] + 10) + (b2[i] - 9)) >> 1);
        }
    }
}

