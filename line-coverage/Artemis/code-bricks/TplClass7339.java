import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class TplClass7339 {

    private static final void method() throws Throwable {
        for (Semaphore sem : new Semaphore[] { new Semaphore(0), new Semaphore(0, false), new Semaphore(0, true) }) for (int delay : new int[] { 0, 1 }) for (int i = 0; i < 3; i++) if (sem.tryAcquire(delay, TimeUnit.MILLISECONDS))
            ;
    }
}

