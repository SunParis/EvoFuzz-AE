import java.util.concurrent.ThreadFactory;
import java.util.Random;
import java.nio.channels.AsynchronousChannelGroup;

public class TplClass5688 {

    private static final void method(java.util.Random rand, java.util.concurrent.ThreadFactory factory, java.nio.channels.AsynchronousChannelGroup group) throws Throwable {
        int nThreads = 1 + rand.nextInt(10);
        group = AsynchronousChannelGroup.withFixedThreadPool(nThreads, factory);
    }
}

