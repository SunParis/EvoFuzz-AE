import java.util.concurrent.ThreadFactory;
import java.nio.channels.AsynchronousChannelGroup;

public class TplClass5650 {

    private static final void method(java.util.concurrent.ThreadFactory threadFactory) throws Throwable {
        try {
            AsynchronousChannelGroup.withFixedThreadPool(1, null);
        } catch (NullPointerException x) {
        }
        try {
            AsynchronousChannelGroup.withFixedThreadPool(0, threadFactory);
        } catch (IllegalArgumentException e) {
        }
        try {
            AsynchronousChannelGroup.withCachedThreadPool(null, 0);
        } catch (NullPointerException x) {
        }
        try {
            AsynchronousChannelGroup.withThreadPool(null);
        } catch (NullPointerException e) {
        }
    }
}

