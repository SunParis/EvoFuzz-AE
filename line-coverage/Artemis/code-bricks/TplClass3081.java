public class TplClass3081 {

    private static final void method(byte[] b2, int i, byte sad, byte[] b1) throws Throwable {
        byte s = b1[i];
        byte p = b2[i];
        int x = s - p;
        if (x < 0)
            x = -x;
        sad += x;
    }
}

