import java.nio.channels.SocketChannel;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;

public class TplClass6709 {

    private static final void method() throws Throwable {
        Selector sa = Selector.open();
        Selector sb = Selector.open();
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        SelectionKey sk = sc.register(sa, SelectionKey.OP_READ);
        sc.register(sb, SelectionKey.OP_READ);
        sc.keyFor(sa).cancel();
        sa.select(1);
        sc.close();
        sa.select(1);
        sb.select(1);
        if (sa.keys().size() > 0)
            ;
        if (sb.keys().size() > 0)
            ;
        sa.close();
        sb.close();
    }
}

