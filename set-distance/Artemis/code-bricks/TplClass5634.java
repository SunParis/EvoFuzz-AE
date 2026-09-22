import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.ShutdownChannelGroupException;

public class TplClass5634 {

    private static final void method(java.nio.channels.AsynchronousChannelGroup group) throws Throwable {
        // attempt to create another channel
        try {
            AsynchronousSocketChannel.open(group);
        } catch (ShutdownChannelGroupException x) {
        }
    }
}

