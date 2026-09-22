import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.BlockingQueue;

public class TplClass6924 {

    private static final void method(java.util.concurrent.BlockingQueue<java.lang.Integer> q, boolean fair) throws Throwable {
        final ReentrantLock lock = new ReentrantLock();
        final Condition ready = lock.newCondition();
        final int threadCount = 10;
        final Throwable[] badness = new Throwable[1];
        lock.lock();
        for (int i = 0; i < threadCount; i++) {
            final Integer I = i;
            Thread t = new Thread() {

                public void run() {
                    try {
                        lock.lock();
                        ready.signal();
                        lock.unlock();
                        q.put(I);
                    } catch (Throwable t) {
                        badness[0] = t;
                    }
                }
            };
            t.start();
            ready.await();
            // Probably unnecessary, but should be bullet-proof
            while (t.getState() == Thread.State.RUNNABLE) Thread.yield();
        }
        for (int i = 0; i < threadCount; i++) {
            int j = q.take();
            // Non-fair queues are lifo in our implementation
            if (fair ? j != i : j != threadCount - 1 - i)
                ;
        }
        if (badness[0] != null)
            ;
    }
}

