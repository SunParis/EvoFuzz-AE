public class TplClass6602 {

    private static final void method(int threadId) throws Throwable {
        for (int j = 0; j < threadId; j++) Thread.currentThread().yield();
    }
}

