public class TplClass999 {

    private static final void method(java.lang.Thread myInitialThread, boolean myShouldCheckThreads) throws Throwable {
        // JIT BUG IS HERE ==>>>>>
        if (myShouldCheckThreads) {
            if (myInitialThread == null) {
                myInitialThread = Thread.currentThread();
            } else if (myInitialThread != Thread.currentThread()) {
            }
        }
    }
}

