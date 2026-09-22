import java.nio.channels.DatagramChannel;
import java.net.SocketAddress;
import java.nio.ByteBuffer;

public class TplClass6527 {

    private static final void method(java.net.SocketAddress remote, java.nio.channels.DatagramChannel dc) throws Throwable {
        ByteBuffer bb = ByteBuffer.wrap("Greetings!".getBytes());
        dc.send(bb, remote);
    }
}

