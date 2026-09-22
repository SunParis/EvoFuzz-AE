import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class TplClass5457 {

    private static final void method(java.nio.channels.DatagramChannel dc) throws Throwable {
        ByteBuffer bb = ByteBuffer.allocate(1024);
        SocketAddress sa = dc.receive(bb);
        bb.flip();
        dc.send(bb, sa);
        dc.close();
    }
}

