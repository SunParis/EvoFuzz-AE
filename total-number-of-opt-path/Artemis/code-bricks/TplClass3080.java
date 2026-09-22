public class TplClass3080 {

    private static final void method(byte[] b2, int i, byte sad, byte[] b1) throws Throwable {
        byte s = b1[i];
        byte p = b2[i];
        sad += s >= p ? s - p : p - s;
    }
}

