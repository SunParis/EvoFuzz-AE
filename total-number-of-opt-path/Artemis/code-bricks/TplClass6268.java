import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;

public class TplClass6268 {

    private static final void method(java.nio.channels.SelectableChannel[] channels, int i) throws Throwable {
        SelectableChannel channel = channels[i];
        channel.close();
        try {
            channel.configureBlocking(true);
        } catch (ClosedChannelException e) {
            // Correct result
        }
    }
}

