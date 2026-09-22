import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.net.SocketAddress;

public class TplClass6546 {

    private static final void method(java.nio.channels.DatagramChannel dgChannel, java.nio.ByteBuffer data, java.net.SocketAddress sa) throws Throwable {
        int n = dgChannel.send(data, sa);
    }
}

