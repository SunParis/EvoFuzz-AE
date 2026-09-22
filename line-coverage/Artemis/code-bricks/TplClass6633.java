import java.nio.channels.SocketChannel;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;

public class TplClass6633 {

    private static final void method(int i, java.nio.channels.Selector sel, java.nio.channels.SocketChannel[] channels) throws Throwable {
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        sc.register(sel, SelectionKey.OP_CONNECT);
        channels[i] = sc;
    }
}

