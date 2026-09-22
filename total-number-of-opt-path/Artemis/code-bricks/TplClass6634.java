import java.nio.channels.SocketChannel;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;

public class TplClass6634 {

    private static final void method(java.nio.channels.Selector sel) throws Throwable {
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        sc.register(sel, SelectionKey.OP_CONNECT);
        // cause helper to spin up
        sel.selectNow();
        sc.close();
        // cause helper to retire
        sel.selectNow();
    }
}

