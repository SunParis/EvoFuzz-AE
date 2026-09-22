public class TplClass1621 {

    private static final void method(int ntries, java.lang.Thread workerThread) throws Throwable {
        workerThread.interrupt();
        if (workerThread.isInterrupted()) {
        }
        ntries++;
        // Wait 1 sec and try again
        workerThread.join(1000);
    }
}

