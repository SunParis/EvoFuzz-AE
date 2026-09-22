import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.net.InetSocketAddress;

public class TplClass6379 {

    private static final void method() throws Throwable {
        try {
            SocketChannel chan1 = SocketChannel.open();
            chan1.socket().bind(new InetSocketAddress(0));
            chan1.socket().bind(new InetSocketAddress(0));
        } catch (IOException e) {
            // Expepected result
        }
    }
}

