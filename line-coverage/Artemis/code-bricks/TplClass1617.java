public class TplClass1617 {

    private static final void method(int ntries, java.lang.Thread workerThread) throws Throwable {
        while (workerThread.isAlive() && ntries < 5) {
            workerThread.interrupt();
            if (workerThread.isInterrupted()) {
            }
            ntries++;
            // Wait 1 sec and try again
            workerThread.join(1000);
        }
    }
}

