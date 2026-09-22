public class TplClass3087 {

    private static final void method(byte[] b2, int i, int sad, byte[] b1) throws Throwable {
        byte s = b1[i];
        byte p = b2[i];
        int x = s - p;
        if (x < 0)
            x = -x;
        sad += x;
    }
}

