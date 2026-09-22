import java.nio.channels.SocketChannel;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;
import java.nio.channels.ClosedChannelException;

public class TplClass7786 {

    private static final void method(java.nio.channels.SocketChannel sc, java.nio.channels.Selector sel, java.nio.channels.SelectionKey key) throws Throwable {
        try {
            key = sc.register(sel, SelectionKey.OP_READ);
        } catch (ClosedChannelException ignore) {
        }
    }
}

