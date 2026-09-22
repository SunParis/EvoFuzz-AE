public class TplClass3176 {

    private static final void method(int i) throws Throwable {
        try {
            byte[] b = new byte[i * 100 + 10];
        } catch (OutOfMemoryError e) {
            // Ignore. This is just to improve chances that an OOME is thrown during
            // proxy invocation.
        }
    }
}

