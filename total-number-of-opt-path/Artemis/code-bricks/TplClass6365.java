import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class TplClass6365 {

    private static final void method(int port, java.nio.channels.SocketChannel sc2) throws Throwable {
        try {
            sc2.bind(new InetSocketAddress(port));
        } finally {
            sc2.close();
        }
    }
}

