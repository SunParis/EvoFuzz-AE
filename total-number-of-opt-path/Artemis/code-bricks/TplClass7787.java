import java.nio.channels.SocketChannel;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;

public class TplClass7787 {

    private static final void method(java.nio.channels.SocketChannel sc, java.nio.channels.Selector sel, java.nio.channels.SelectionKey key) throws Throwable {
        key = sc.register(sel, SelectionKey.OP_READ);
    }
}

