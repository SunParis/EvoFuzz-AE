public class TplClass3086 {

    private static final void method(byte[] b2, int i, int sad, byte[] b1) throws Throwable {
        byte s = b1[i];
        byte p = b2[i];
        sad += s >= p ? s - p : p - s;
    }
}

