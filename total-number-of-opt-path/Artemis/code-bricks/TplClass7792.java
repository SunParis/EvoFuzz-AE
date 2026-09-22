import java.util.concurrent.atomic.AtomicBoolean;
import java.nio.channels.SelectionKey;
import java.util.concurrent.atomic.AtomicInteger;

public class TplClass7792 {

    private static final void method(java.util.concurrent.atomic.AtomicBoolean att, java.util.concurrent.atomic.AtomicInteger errorCount, java.nio.channels.SelectionKey key) throws Throwable {
        att = (AtomicBoolean) key.attach(att);
        // We should have exclusive ownership of att.
        if (!att.compareAndSet(false, true) || !att.compareAndSet(true, false)) {
            errorCount.incrementAndGet();
        }
    }
}

