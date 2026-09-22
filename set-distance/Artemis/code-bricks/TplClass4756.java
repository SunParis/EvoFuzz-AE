import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TplClass4756 {

    private static final void method() throws Throwable {
        final CountDownLatch count = new CountDownLatch(1000);
        final ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(100);
        pool.prestartAllCoreThreads();
        final Runnable incTask = new Runnable() {

            public void run() {
                count.countDown();
            }
        };
        pool.scheduleAtFixedRate(incTask, 0, 10, TimeUnit.MILLISECONDS);
        count.await();
        pool.shutdown();
        pool.awaitTermination(1L, TimeUnit.DAYS);
    }
}

