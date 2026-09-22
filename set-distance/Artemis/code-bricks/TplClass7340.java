import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class TplClass7340 {

    private static final void method(java.util.concurrent.Semaphore sem) throws Throwable {
        for (int delay : new int[] { 0, 1 }) for (int i = 0; i < 3; i++) if (sem.tryAcquire(delay, TimeUnit.MILLISECONDS))
            ;
    }
}

