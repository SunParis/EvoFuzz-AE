public class TplClass2832 {

    private static final void method(java.lang.OutOfMemoryError expected) throws Throwable {
        // TODO: fix bionic bug https://b/6702535 so we can check the full detail message.
        if (!expected.getMessage().startsWith("pthread_create (3073MB stack) failed: ")) {
        }
    }
}

