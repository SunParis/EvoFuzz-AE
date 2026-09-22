public class TplClass3434 {

    private static final void method(short[] b2, int min_length, short[] bo, short[] b1) throws Throwable {
        for (int i = 0; i < min_length; i++) {
            // Slightly different order in idiom does not confuse recognition.
            bo[i] = (short) (((1 + b1[i]) + b2[i]) >> 1);
        }
    }
}

