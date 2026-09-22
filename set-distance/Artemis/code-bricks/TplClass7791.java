import java.util.concurrent.atomic.AtomicBoolean;
import java.nio.channels.SelectionKey;
import java.util.concurrent.atomic.AtomicInteger;

public class TplClass7791 {

    private static final void method(java.util.concurrent.atomic.AtomicInteger errorCount, java.nio.channels.SelectionKey key) throws Throwable {
        AtomicBoolean att = new AtomicBoolean();
        for (int i = 0; i < (10 * 1000 * 1000); i++) {
            att = (AtomicBoolean) key.attach(att);
            // We should have exclusive ownership of att.
            if (!att.compareAndSet(false, true) || !att.compareAndSet(true, false)) {
                errorCount.incrementAndGet();
            }
        }
    }
}

