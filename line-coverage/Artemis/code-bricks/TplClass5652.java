import java.util.concurrent.ThreadFactory;
import java.nio.channels.AsynchronousChannelGroup;

public class TplClass5652 {

    private static final void method(java.util.concurrent.ThreadFactory threadFactory) throws Throwable {
        AsynchronousChannelGroup.withFixedThreadPool(0, threadFactory);
    }
}

