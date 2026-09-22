import java.nio.ByteBuffer;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class TplClass6548 {

    private static final void method(java.nio.ByteBuffer bb, java.net.SocketAddress sa, java.nio.channels.DatagramChannel dc) throws Throwable {
        for (int i = 0; i < 100; i++) {
            bb.clear();
            sa = dc.receive(bb);
            if (sa != null)
                ;
        }
    }
}

