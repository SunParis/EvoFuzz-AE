import java.util.concurrent.ThreadFactory;
import java.nio.channels.AsynchronousChannelGroup;

public class TplClass5640 {

    private static final void method(java.util.concurrent.ThreadFactory threadFactory) throws Throwable {
        try {
            AsynchronousChannelGroup.withFixedThreadPool(0, threadFactory);
        } catch (IllegalArgumentException e) {
        }
    }
}

