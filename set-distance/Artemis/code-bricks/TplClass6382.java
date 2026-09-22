import java.nio.channels.SocketChannel;
import java.net.InetSocketAddress;

public class TplClass6382 {

    private static final void method() throws Throwable {
        SocketChannel chan1 = SocketChannel.open();
        chan1.socket().bind(new InetSocketAddress(0));
        chan1.socket().bind(new InetSocketAddress(0));
    }
}

