import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ForkJoinPool;

public class TplClass7220 {

    private static final void method(java.util.concurrent.atomic.AtomicBoolean b, java.util.concurrent.ForkJoinPool e, java.lang.Runnable setFalse) throws Throwable {
        b.set(true);
        e.execute(setFalse);
        long st = System.nanoTime();
        while (b.get()) {
            if (System.nanoTime() - st >= TimeUnit.SECONDS.toNanos(10)) {
            }
        }
    }
}

