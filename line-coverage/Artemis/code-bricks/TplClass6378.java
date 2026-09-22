import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;
import java.net.InetSocketAddress;

public class TplClass6378 {

    private static final void method(java.net.InetSocketAddress iAddr) throws Throwable {
        try {
            SocketChannel channel = SocketChannel.open();
            channel.socket().connect(iAddr, 30000);
        } catch (UnknownHostException x) {
            // Expected result
        }
    }
}

