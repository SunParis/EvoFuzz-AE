import java.nio.channels.ServerSocketChannel;
import java.net.InetSocketAddress;

public class TplClass6411 {

    private static final void method(java.lang.Process p, java.nio.channels.ServerSocketChannel ssc, int port) throws Throwable {
        // this will fail
        try {
            ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress(port));
            ssc.close();
        } finally {
            p.destroy();
        }
    }
}

