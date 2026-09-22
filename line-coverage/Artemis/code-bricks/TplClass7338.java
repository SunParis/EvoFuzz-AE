import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class TplClass7338 {

    private static final void method(java.util.concurrent.Semaphore sem, int delay) throws Throwable {
        for (int i = 0; i < 3; i++) if (sem.tryAcquire(delay, TimeUnit.MILLISECONDS))
            ;
    }
}

