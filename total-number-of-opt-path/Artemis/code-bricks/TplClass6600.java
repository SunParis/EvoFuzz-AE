public class TplClass6600 {

    private static final void method(int threadId, int[] x) throws Throwable {
        x[threadId]++;
        Thread.currentThread().yield();
    }
}

