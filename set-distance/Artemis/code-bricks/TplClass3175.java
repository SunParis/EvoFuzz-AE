public class TplClass3175 {

    private static final void method(long elapsed, long start, long delta) throws Throwable {
        do {
            elapsed = System.nanoTime();
        } while (elapsed - start < delta);
    }
}

