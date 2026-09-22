import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;

public class TplClass6706 {

    private static final void method(java.nio.channels.SelectionKey sk, java.nio.channels.ServerSocketChannel ssc, java.net.InetSocketAddress isa) throws Throwable {
        SocketChannel sc = SocketChannel.open();
        sc.connect(isa);
        ssc.close();
        sk.cancel();
        sc.close();
    }
}

