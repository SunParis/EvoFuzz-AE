import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class TplClass5227 {

    private static final void method(java.util.concurrent.BlockingQueue[] qs, long end) throws Throwable {
        while (System.currentTimeMillis() < end) for (BlockingQueue q : qs) q.poll(1, TimeUnit.NANOSECONDS);
    }
}

