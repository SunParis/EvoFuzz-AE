import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ClosedSelectorException;

public class TplClass6714 {

    private static final void method() throws Throwable {
        Selector sel = Selector.open();
        sel.close();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        try {
            ssc.bind(new InetSocketAddress(0));
            ssc.configureBlocking(false);
            ssc.register(sel, SelectionKey.OP_ACCEPT);
        } catch (ClosedSelectorException cse) {
            // expected
        } finally {
            ssc.close();
        }
    }
}

