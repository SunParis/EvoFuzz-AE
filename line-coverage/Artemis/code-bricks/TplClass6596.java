public class TplClass6596 {

    private static final void method(int threadId, int[] x) throws Throwable {
        for (int j = 0; j < threadId; j++) {
            x[threadId]++;
            Thread.currentThread().yield();
        }
    }
}

