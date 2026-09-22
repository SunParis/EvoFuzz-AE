import java.util.concurrent.ConcurrentLinkedQueue;

public class TplClass6587 {

    private static final void method(int i, int iterations) throws Throwable {
        ConcurrentLinkedQueue<Long> queue = new ConcurrentLinkedQueue<>();
        queue.add(0L);
        while (i++ < iterations) {
            queue.add(1L);
            queue.remove(1L);
        }
    }
}

