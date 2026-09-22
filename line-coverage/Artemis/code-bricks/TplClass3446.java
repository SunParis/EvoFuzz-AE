public class TplClass3446 {

    private static final void method(short[] b2, int i, short[] bo, short[] b1) throws Throwable {
        int v1 = b1[i] & 0xffff;
        int v2 = b2[i] & 0xffff;
        bo[i] = (short) ((v1 + v2) >> 1);
    }
}

