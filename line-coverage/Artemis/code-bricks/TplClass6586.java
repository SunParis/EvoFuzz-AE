import java.util.concurrent.ConcurrentLinkedQueue;

public class TplClass6586 {

    private static final void method() throws Throwable {
        int i = 0;
        // Without bug fix, OutOfMemoryError was observed at iteration 65120
        int iterations = 10 * 65120;
        try {
            ConcurrentLinkedQueue<Long> queue = new ConcurrentLinkedQueue<>();
            queue.add(0L);
            while (i++ < iterations) {
                queue.add(1L);
                queue.remove(1L);
            }
        } catch (Error t) {
        }
    }
}

