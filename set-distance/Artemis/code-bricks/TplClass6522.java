import java.nio.channels.DatagramChannel;
import java.nio.channels.Selector;
import java.net.SocketAddress;

public class TplClass6522 {

    private static final void method(java.net.SocketAddress refuser, java.nio.channels.Selector sel) throws Throwable {
        sel.selectedKeys().clear();
        // our expected refuser port, cannot run just exit.
        DatagramChannel.open().bind(refuser).close();
    }
}

