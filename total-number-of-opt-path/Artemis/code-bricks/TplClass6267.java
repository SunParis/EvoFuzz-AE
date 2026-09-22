import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SocketChannel;

public class TplClass6267 {

    private static final void method() throws Throwable {
        SelectableChannel[] channels = null;
        channels = new SelectableChannel[] { DatagramChannel.open(), SocketChannel.open(), ServerSocketChannel.open() };
        for (int i = 0; i < channels.length; i++) {
            SelectableChannel channel = channels[i];
            channel.close();
            try {
                channel.configureBlocking(true);
            } catch (ClosedChannelException e) {
                // Correct result
            }
        }
    }
}

