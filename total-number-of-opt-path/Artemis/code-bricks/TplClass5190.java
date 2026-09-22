import java.util.concurrent.TimeUnit;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class TplClass5190 {

    private static final void method(java.util.concurrent.ScheduledThreadPoolExecutor pool, int size, java.lang.Runnable nop) throws Throwable {
        for (int i = 0; i < size; i++) pool.scheduleAtFixedRate(nop, 100L * (i + 1), 1000L, TimeUnit.MILLISECONDS);
    }
}

