import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;

public class TplClass6918 {

    private static final void method(java.lang.Throwable[] badness, java.util.concurrent.BlockingQueue<java.lang.Integer> q, java.util.concurrent.locks.ReentrantLock lock, int threadCount, java.util.concurrent.locks.Condition ready) throws Throwable {
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
    }
}

