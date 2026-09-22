import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.ShutdownChannelGroupException;

public class TplClass5635 {

    private static final void method(java.nio.channels.AsynchronousChannelGroup group) throws Throwable {
        try {
            AsynchronousServerSocketChannel.open(group);
        } catch (ShutdownChannelGroupException x) {
        }
    }
}

