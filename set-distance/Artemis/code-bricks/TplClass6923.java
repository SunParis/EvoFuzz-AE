import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;

public class TplClass6923 {

    private static final void method(java.lang.Throwable[] badness, java.util.concurrent.BlockingQueue<java.lang.Integer> q, java.lang.Integer I, java.util.concurrent.locks.ReentrantLock lock, java.util.concurrent.locks.Condition ready) throws Throwable {
        try {
            lock.lock();
            ready.signal();
            lock.unlock();
            q.put(I);
        } catch (Throwable t) {
            badness[0] = t;
        }
    }
}

