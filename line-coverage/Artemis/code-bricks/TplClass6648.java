import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.DatagramChannel;

public class TplClass6648 {

    private static final void method(java.nio.channels.DatagramChannel ch) throws Throwable {
        try {
            ch.configureBlocking(false);
            Selector sel = Selector.open();
            SelectionKey key = ch.register(sel, SelectionKey.OP_WRITE);
            sel.close();
            if (key.isValid())
                ;
        } finally {
            ch.close();
        }
    }
}

