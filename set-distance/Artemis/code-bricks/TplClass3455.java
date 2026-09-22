public class TplClass3455 {

    private static final void method(short[] b2, short[] bo, short[] b1) throws Throwable {
        int min_length = Math.min(bo.length, Math.min(b1.length, b2.length));
        for (int i = 0; i < min_length; i++) {
            // Slightly different order in idiom does not confuse recognition.
            int v1 = b1[i] & 0xffff;
            int v2 = b2[i] & 0xffff;
            bo[i] = (short) (v1 + (v2 + 1) >> 1);
        }
    }
}

