import java.nio.channels.AsynchronousChannelGroup;

public class TplClass5641 {

    private static final void method() throws Throwable {
        try {
            AsynchronousChannelGroup.withCachedThreadPool(null, 0);
        } catch (NullPointerException x) {
        }
    }
}

