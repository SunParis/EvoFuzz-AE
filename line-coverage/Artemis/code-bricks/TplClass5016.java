import java.nio.channels.SocketChannel;
import java.net.InetAddress;
import java.nio.channels.ServerSocketChannel;
import java.net.SocketAddress;
import java.nio.channels.ClosedChannelException;
import java.net.InetSocketAddress;

public class TplClass5016 {

    private static final void method() throws Throwable {
        ServerSocketChannel ssc = ServerSocketChannel.open().bind(new InetSocketAddress(0));
        InetSocketAddress local = (InetSocketAddress) (ssc.getLocalAddress());
        int port = local.getPort();
        InetSocketAddress server = new InetSocketAddress(InetAddress.getLocalHost(), port);
        SocketChannel sc = SocketChannel.open();
        // not connected
        if (sc.getRemoteAddress() != null)
            ;
        // connected
        sc.connect(server);
        SocketAddress remote = sc.getRemoteAddress();
        if (!remote.equals(server))
            ;
        // closed
        sc.close();
        try {
            sc.getRemoteAddress();
        } catch (ClosedChannelException e) {
        }
        ssc.close();
    }
}

