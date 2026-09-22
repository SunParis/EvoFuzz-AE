public class TplClass3180 {

    private static final void method() throws Throwable {
        // "Wait" a small amount of time.
        long start = System.nanoTime();
        // 10 us.
        long delta = 10 * 1000;
        long elapsed;
        do {
            elapsed = System.nanoTime();
        } while (elapsed - start < delta);
    }
}

