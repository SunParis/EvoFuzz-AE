import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class TplClass5226 {

    private static final void method(java.util.concurrent.BlockingQueue[] qs) throws Throwable {
        for (BlockingQueue q : qs) q.poll(1, TimeUnit.NANOSECONDS);
    }
}

