import java.util.concurrent.atomic.AtomicInteger;

public class TplClass3599 {

    private static final void method(int NUMBER_OF_THREADS, java.util.concurrent.atomic.AtomicInteger[] targetted, java.util.concurrent.atomic.AtomicInteger[] called) throws Throwable {
        // Initialize counters for which call site gets invoked
        for (int i = 0; i < NUMBER_OF_THREADS; ++i) {
            called[i] = new AtomicInteger(0);
            targetted[i] = new AtomicInteger(0);
        }
    }
}

