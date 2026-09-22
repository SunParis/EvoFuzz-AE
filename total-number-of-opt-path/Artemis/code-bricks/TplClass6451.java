import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.net.InetSocketAddress;

public class TplClass6451 {

    private static final void method(java.nio.channels.ServerSocketChannel ssc) throws Throwable {
        try {
            ssc = ServerSocketChannel.open();
            ssc.bind(new InetSocketAddress((0)));
        } catch (IOException ex) {
        }
    }
}

