import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.net.SocketAddress;
import java.net.InetAddress;

public class TplClass6540 {

    private static final void method(java.nio.channels.DatagramChannel server, java.nio.channels.DatagramChannel client, java.net.InetSocketAddress isa) throws Throwable {
        client = DatagramChannel.open();
        server = DatagramChannel.open();
        client.socket().bind((SocketAddress) null);
        server.socket().bind((SocketAddress) null);
        client.configureBlocking(false);
        server.configureBlocking(false);
        InetAddress address = InetAddress.getLocalHost();
        int port = client.socket().getLocalPort();
        isa = new InetSocketAddress(address, port);
    }
}

