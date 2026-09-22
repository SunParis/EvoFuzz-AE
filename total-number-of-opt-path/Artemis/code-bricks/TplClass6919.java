import java.util.concurrent.BlockingQueue;

public class TplClass6919 {

    private static final void method(java.util.concurrent.BlockingQueue<java.lang.Integer> q, int threadCount, boolean fair) throws Throwable {
        for (int i = 0; i < threadCount; i++) {
            int j = q.take();
            // Non-fair queues are lifo in our implementation
            if (fair ? j != i : j != threadCount - 1 - i)
                ;
        }
    }
}

