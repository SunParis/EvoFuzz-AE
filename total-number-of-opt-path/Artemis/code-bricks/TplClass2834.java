public class TplClass2834 {

    private static final void method() throws Throwable {
        Thread t = new Thread(null, new Runnable() {

            public void run() {
            }
        }, "", 3L * 1024 * 1024 * 1024);
        try {
            t.start();
        } catch (OutOfMemoryError expected) {
            // TODO: fix bionic bug https://b/6702535 so we can check the full detail message.
            if (!expected.getMessage().startsWith("pthread_create (3073MB stack) failed: ")) {
            }
        }
    }
}

