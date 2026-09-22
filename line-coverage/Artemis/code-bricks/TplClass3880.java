public class TplClass3880 {

    private static final void method(boolean parkNow) throws Throwable {
        while (!parkNow) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                // Ignore it.
            }
        }
    }
}

