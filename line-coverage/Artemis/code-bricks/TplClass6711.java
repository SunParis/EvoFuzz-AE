import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class TplClass6711 {

    private static final void method() throws Throwable {
        Selector sel = Selector.open();
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        SelectionKey key = sc.register(sel, 0);
        for (int i = 0; i < 50000; i++) {
            key.interestOps(0);
        }
        sel.selectNow();
    }
}

