import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ClosedSelectorException;

public class TplClass6713 {

    private static final void method(java.nio.channels.ServerSocketChannel ssc, java.nio.channels.Selector sel) throws Throwable {
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

