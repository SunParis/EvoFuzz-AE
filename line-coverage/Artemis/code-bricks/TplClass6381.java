import java.nio.channels.SocketChannel;
import java.net.InetSocketAddress;

public class TplClass6381 {

    private static final void method(java.net.InetSocketAddress iAddr) throws Throwable {
        SocketChannel channel = SocketChannel.open();
        channel.socket().connect(iAddr, 30000);
    }
}

