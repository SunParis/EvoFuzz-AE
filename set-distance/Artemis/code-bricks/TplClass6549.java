import java.nio.ByteBuffer;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class TplClass6549 {

    private static final void method(java.nio.ByteBuffer bb, java.net.SocketAddress sa, java.nio.channels.DatagramChannel dc) throws Throwable {
        while (sa == null) sa = dc.receive(bb);
    }
}

