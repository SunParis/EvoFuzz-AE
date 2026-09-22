import java.nio.channels.NetworkChannel;
import java.nio.channels.ClosedChannelException;
import java.net.InetSocketAddress;

public class TplClass5011 {

    private static final void method(java.nio.channels.NetworkChannel ch) throws Throwable {
        // ClosedChannelException
        try {
            ch.bind(new InetSocketAddress(0));
        } catch (ClosedChannelException x) {
        }
    }
}

