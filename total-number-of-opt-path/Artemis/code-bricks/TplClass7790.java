import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class TplClass7790 {

    private static final void method(java.util.concurrent.atomic.AtomicBoolean att, java.util.concurrent.atomic.AtomicInteger errorCount) throws Throwable {
        // We should have exclusive ownership of att.
        if (!att.compareAndSet(false, true) || !att.compareAndSet(true, false)) {
            errorCount.incrementAndGet();
        }
    }
}

