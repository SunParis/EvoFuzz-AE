public class TplClass3179 {

    private static final void method(int count, java.lang.Object lockObject) throws Throwable {
        synchronized (lockObject) {
            // "Wait" a small amount of time.
            long start = System.nanoTime();
            // 10 us.
            long delta = 10 * 1000;
            long elapsed;
            do {
                elapsed = System.nanoTime();
            } while (elapsed - start < delta);
        }
        count--;
    }
}

