import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ForkJoinPool;

public class TplClass7219 {

    private static final void method() throws Throwable {
        final ForkJoinPool e = new ForkJoinPool(1);
        final AtomicBoolean b = new AtomicBoolean();
        final Runnable setFalse = () -> b.set(false);
        for (int i = 0; i < 100000; i++) {
            b.set(true);
            e.execute(setFalse);
            long st = System.nanoTime();
            while (b.get()) {
                if (System.nanoTime() - st >= TimeUnit.SECONDS.toNanos(10)) {
                }
            }
        }
    }
}

