import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class TplClass7341 {

    private static final void method(java.util.concurrent.Semaphore sem, int delay) throws Throwable {
        if (sem.tryAcquire(delay, TimeUnit.MILLISECONDS))
            ;
    }
}

