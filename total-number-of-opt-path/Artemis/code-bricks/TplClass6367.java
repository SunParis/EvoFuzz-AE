import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class TplClass6367 {

    private static final void method(java.nio.channels.SocketChannel sc1) throws Throwable {
        sc1.bind(new InetSocketAddress(0));
        int port = sc1.socket().getLocalPort();
        SocketChannel sc2 = SocketChannel.open();
        try {
            sc2.bind(new InetSocketAddress(port));
        } finally {
            sc2.close();
        }
    }
}

