import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;

public class TplClass6266 {

    private static final void method(java.nio.channels.SelectableChannel channel) throws Throwable {
        try {
            channel.configureBlocking(true);
        } catch (ClosedChannelException e) {
            // Correct result
        }
    }
}

