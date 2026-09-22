import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.Random;
import java.util.concurrent.Executors;
import java.nio.channels.AsynchronousChannelGroup;

public class TplClass5683 {

    private static final void method(java.util.Random rand, java.util.concurrent.ThreadFactory factory, java.nio.channels.AsynchronousChannelGroup group) throws Throwable {
        if (rand.nextBoolean()) {
            int nThreads = 1 + rand.nextInt(10);
            group = AsynchronousChannelGroup.withFixedThreadPool(nThreads, factory);
        } else {
            ExecutorService pool = Executors.newCachedThreadPool(factory);
            group = AsynchronousChannelGroup.withCachedThreadPool(pool, rand.nextInt(5));
        }
    }
}

