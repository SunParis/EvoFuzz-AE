import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.net.SocketAddress;

public class TplClass6560 {

    private static final void method(java.net.SocketAddress sa, java.nio.channels.DatagramChannel dc) throws Throwable {
        ByteBuffer bb = ByteBuffer.allocateDirect(12);
        bb.clear();
        // Get the one valid datagram
        dc.configureBlocking(false);
        while (sa == null) sa = dc.receive(bb);
        sa = null;
        for (int i = 0; i < 100; i++) {
            bb.clear();
            sa = dc.receive(bb);
            if (sa != null)
                ;
        }
        dc.close();
    }
}

