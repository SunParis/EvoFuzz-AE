public class TplClass2833 {

    private static final void method(java.lang.Thread t) throws Throwable {
        try {
            t.start();
        } catch (OutOfMemoryError expected) {
            // TODO: fix bionic bug https://b/6702535 so we can check the full detail message.
            if (!expected.getMessage().startsWith("pthread_create (3073MB stack) failed: ")) {
            }
        }
    }
}

