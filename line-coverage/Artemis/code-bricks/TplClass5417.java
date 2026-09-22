import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.StampedLock;

public class TplClass5417 {

    private static final void method(java.util.concurrent.locks.StampedLock sl, java.util.concurrent.CyclicBarrier iterationStart, java.util.concurrent.CyclicBarrier readersHaveLocks, java.util.concurrent.CyclicBarrier writerHasLock) throws Throwable {
        iterationStart.await();
        writerHasLock.await();
        long rs = sl.readLock();
        // are serialized
        readersHaveLocks.await();
        sl.unlockRead(rs);
    }
}

