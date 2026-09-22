import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.StampedLock;

public class TplClass5414 {

    private static final void method(java.util.concurrent.locks.StampedLock sl, int i, java.util.concurrent.CyclicBarrier iterationStart, java.util.concurrent.CyclicBarrier writerHasLock) throws Throwable {
        for (i = 0; i < 1024; ++i) {
            try {
                iterationStart.await();
                long ws = sl.writeLock();
                writerHasLock.await();
                Thread.sleep(10);
                sl.unlockWrite(ws);
            } catch (Exception e) {
            }
        }
    }
}

