public class TplClass3452 {

    private static final void method(short[] b2, int i, short[] bo, short[] b1) throws Throwable {
        // Computations that cancel to adding 1 also do not confuse recognition.
        bo[i] = (short) (((b1[i] + 10) + (b2[i] - 9)) >> 1);
    }
}

