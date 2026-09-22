import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

public class TplClass6715 {

    private static final void method(java.nio.channels.ServerSocketChannel ssc, java.nio.channels.Selector sel) throws Throwable {
        ssc.bind(new InetSocketAddress(0));
        ssc.configureBlocking(false);
        ssc.register(sel, SelectionKey.OP_ACCEPT);
    }
}

