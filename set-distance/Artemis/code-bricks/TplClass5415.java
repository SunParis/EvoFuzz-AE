import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.StampedLock;

public class TplClass5415 {

    private static final void method(java.util.concurrent.locks.StampedLock sl, java.util.concurrent.CyclicBarrier iterationStart, java.util.concurrent.CyclicBarrier readersHaveLocks, java.util.concurrent.CyclicBarrier writerHasLock) throws Throwable {
        try {
            iterationStart.await();
            writerHasLock.await();
            long rs = sl.readLock();
            // single reader blocks here indefinitely if readers
            // are serialized
            readersHaveLocks.await();
            sl.unlockRead(rs);
        } catch (Exception e) {
        }
    }
}

