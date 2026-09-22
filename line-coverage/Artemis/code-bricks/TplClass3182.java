public class TplClass3182 {

    private static final void method() throws Throwable {
        // Some random allocations adding up to almost 2M.
        for (int i = 0; i < 188; i++) {
            try {
                byte[] b = new byte[i * 100 + 10];
            } catch (OutOfMemoryError e) {
                // Ignore. This is just to improve chances that an OOME is thrown during
                // proxy invocation.
            }
        }
        try {
            Thread.sleep(10);
        } catch (Exception e) {
        }
    }
}

