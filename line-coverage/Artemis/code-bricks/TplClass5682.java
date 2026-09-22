import java.nio.channels.AsynchronousChannelGroup;

public class TplClass5682 {

    private static final void method(java.nio.channels.AsynchronousChannelGroup[] groups) throws Throwable {
        for (AsynchronousChannelGroup group : groups) group.shutdownNow();
    }
}

