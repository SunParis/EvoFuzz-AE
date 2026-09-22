public class TplClass3172 {

    private static final void method(java.lang.Thread[] threads) throws Throwable {
        // Now wait.
        for (Thread t : threads) {
            t.join();
        }
    }
}

