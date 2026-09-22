import java.nio.ByteBuffer;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class TplClass6561 {

    private static final void method(java.nio.ByteBuffer bb, java.net.SocketAddress sa, java.nio.channels.DatagramChannel dc) throws Throwable {
        bb.clear();
        sa = dc.receive(bb);
        if (sa != null)
            ;
    }
}

