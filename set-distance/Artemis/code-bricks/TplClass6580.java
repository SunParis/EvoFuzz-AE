import java.nio.channels.DatagramChannel;
import java.nio.channels.NotYetConnectedException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

public class TplClass6580 {

    private static final void method(java.nio.channels.DatagramChannel dc) throws Throwable {
        try (DatagramChannel server = DatagramChannel.open()) {
            server.bind(new InetSocketAddress(0));
            InetAddress lh = InetAddress.getLocalHost();
            dc.connect(new InetSocketAddress(lh, server.socket().getLocalPort()));
            dc.write(ByteBuffer.wrap("hello".getBytes()));
            ByteBuffer bb = ByteBuffer.allocate(100);
            server.receive(bb);
            dc.disconnect();
            try {
                dc.write(ByteBuffer.wrap("another message".getBytes()));
            } catch (NotYetConnectedException expected) {
            }
        }
    }
}

