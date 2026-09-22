public class TplClass1620 {

    private static final void method() throws Throwable {
        Thread workerThread = new Thread("worker") {

            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                }
            }
        };
        workerThread.start();
        // Wait 5 sec to let run() method to be compiled
        workerThread.join(5000);
        int ntries = 0;
        while (workerThread.isAlive() && ntries < 5) {
            workerThread.interrupt();
            if (workerThread.isInterrupted()) {
            }
            ntries++;
            // Wait 1 sec and try again
            workerThread.join(1000);
        }
        if (ntries == 5) {
        }
    }
}

