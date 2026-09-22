public class TplClass3879 {

    private static final void method() throws Throwable {
        Object[] src = new Object[1024];
        Object[] dst = new Object[2048];
        // System.arraycopy to be intrinsified.
        System.arraycopy(src, 0, dst, 1024, 64);
    }
}

