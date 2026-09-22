public class TplClass3083 {

    private static final void method(byte[] b2, int i, short sad, byte[] b1) throws Throwable {
        byte s = b1[i];
        byte p = b2[i];
        sad += s >= p ? s - p : p - s;
    }
}

