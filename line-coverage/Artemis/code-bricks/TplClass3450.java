public class TplClass3450 {

    private static final void method(short[] b2, int i, short[] bo, short[] b1) throws Throwable {
        // Slightly different order in idiom does not confuse recognition.
        bo[i] = (short) (((1 + b1[i]) + b2[i]) >> 1);
    }
}

