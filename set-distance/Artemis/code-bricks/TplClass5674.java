import java.nio.channels.AsynchronousChannelGroup;

public class TplClass5674 {

    private static final void method(boolean shutdownGroup, java.nio.channels.AsynchronousChannelGroup group) throws Throwable {
        if (shutdownGroup) {
            group.shutdownNow();
        }
    }
}

