public class TplClass3600 {

    private static final void method(int NUMBER_OF_THREADS, java.lang.Thread[] threads) throws Throwable {
        // Wait for all threads to complete
        for (int i = 0; i < NUMBER_OF_THREADS; ++i) {
            threads[i].join();
        }
    }
}

