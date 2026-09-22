import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.Random;
import java.util.concurrent.Executors;
import java.nio.channels.AsynchronousChannelGroup;

public class TplClass5689 {

    private static final void method(java.util.Random rand, java.util.concurrent.ThreadFactory factory, java.nio.channels.AsynchronousChannelGroup group) throws Throwable {
        ExecutorService pool = Executors.newCachedThreadPool(factory);
        group = AsynchronousChannelGroup.withCachedThreadPool(pool, rand.nextInt(5));
    }
}

