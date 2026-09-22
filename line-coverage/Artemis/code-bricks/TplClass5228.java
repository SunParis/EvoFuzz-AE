import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.SynchronousQueue;

public class TplClass5228 {

    private static final void method() throws Throwable {
        final BlockingQueue[] qs = { new LinkedBlockingQueue(10), new LinkedTransferQueue(), new ArrayBlockingQueue(10), new SynchronousQueue(), new SynchronousQueue(true) };
        final long start = System.currentTimeMillis();
        final long end = start + 10 * 1000;
        while (System.currentTimeMillis() < end) for (BlockingQueue q : qs) q.poll(1, TimeUnit.NANOSECONDS);
    }
}

