import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.StampedLock;

public class TplClass5418 {

    private static final void method(java.util.concurrent.locks.StampedLock sl, java.util.concurrent.CyclicBarrier iterationStart, java.util.concurrent.CyclicBarrier writerHasLock) throws Throwable {
        iterationStart.await();
        long ws = sl.writeLock();
        writerHasLock.await();
        Thread.sleep(10);
        sl.unlockWrite(ws);
    }
}

