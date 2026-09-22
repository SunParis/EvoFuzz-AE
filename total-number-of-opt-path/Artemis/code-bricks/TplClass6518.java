import java.nio.channels.Selector;
import java.nio.channels.DatagramChannel;
import java.net.SocketAddress;

public class TplClass6518 {

    private static final void method(java.net.SocketAddress refuser, java.nio.channels.Selector sel, int n) throws Throwable {
        if (n > 0) {
            sel.selectedKeys().clear();
            // BindException will be thrown if another service is using
            // our expected refuser port, cannot run just exit.
            DatagramChannel.open().bind(refuser).close();
        }
    }
}

