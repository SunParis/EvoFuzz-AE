import java.util.concurrent.BlockingQueue;

public class TplClass6928 {

    private static final void method(java.util.concurrent.BlockingQueue<java.lang.Integer> q, int i, int threadCount, boolean fair) throws Throwable {
        int j = q.take();
        // Non-fair queues are lifo in our implementation
        if (fair ? j != i : j != threadCount - 1 - i)
            ;
    }
}

