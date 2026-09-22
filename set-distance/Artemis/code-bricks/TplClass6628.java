import java.nio.channels.SocketChannel;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;

public class TplClass6628 {

    private static final void method(int CHANNELS_PER_THREAD, java.nio.channels.Selector sel, java.nio.channels.SocketChannel[] channels) throws Throwable {
        for (int i = 0; i < CHANNELS_PER_THREAD; i++) {
            SocketChannel sc = SocketChannel.open();
            sc.configureBlocking(false);
            sc.register(sel, SelectionKey.OP_CONNECT);
            channels[i] = sc;
        }
    }
}

