import java.util.concurrent.atomic.AtomicInteger;

public class TplClass3605 {

    private static final void method(int NUMBER_OF_THREADS, int votes, java.util.concurrent.atomic.AtomicInteger[] called) throws Throwable {
        // it's non-deterministic.
        if (votes != NUMBER_OF_THREADS) {
            for (int i = 0; i < NUMBER_OF_THREADS; ++i) {
            }
        }
    }
}

