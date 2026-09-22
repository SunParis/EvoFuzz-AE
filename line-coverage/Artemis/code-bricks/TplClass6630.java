import java.nio.channels.SocketChannel;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;

public class TplClass6630 {

    private static final void method(java.nio.channels.Selector sel, int TEST_ITERATIONS) throws Throwable {
        // Loop changing the number of channels from 1023 to 1024 and back.
        for (int i = 0; i < TEST_ITERATIONS; i++) {
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
}

