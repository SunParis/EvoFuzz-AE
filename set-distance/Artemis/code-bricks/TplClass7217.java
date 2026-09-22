import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class TplClass7217 {

    private static final void method(long st, java.util.concurrent.atomic.AtomicBoolean b) throws Throwable {
        while (b.get()) {
            if (System.nanoTime() - st >= TimeUnit.SECONDS.toNanos(10)) {
            }
        }
    }
}

