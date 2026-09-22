import java.nio.channels.ServerSocketChannel;
import java.net.InetSocketAddress;

public class TplClass6413 {

    private static final void method(int port, java.nio.channels.ServerSocketChannel ssc) throws Throwable {
        ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(port));
        ssc.close();
    }
}

