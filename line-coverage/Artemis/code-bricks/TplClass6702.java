import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.io.IOException;
import java.nio.channels.ServerSocketChannel;

public class TplClass6702 {

    private static final void method(java.nio.channels.SelectionKey sk, java.nio.channels.ServerSocketChannel ssc, java.nio.channels.Selector selector) throws Throwable {
        try {
            ssc.configureBlocking(false);
            sk = ssc.register(selector, SelectionKey.OP_ACCEPT);
            selector.select();
        } catch (IOException e) {
        }
    }
}

