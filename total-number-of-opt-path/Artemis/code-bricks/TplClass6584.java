import java.util.concurrent.ConcurrentLinkedQueue;

public class TplClass6584 {

    private static final void method(int i, java.util.concurrent.ConcurrentLinkedQueue<java.lang.Long> queue, int iterations) throws Throwable {
        while (i++ < iterations) {
            queue.add(1L);
            queue.remove(1L);
        }
    }
}

