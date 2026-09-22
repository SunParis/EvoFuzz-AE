import java.nio.channels.DatagramChannel;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;

public class TplClass6517 {

    private static final void method() throws Throwable {
        DatagramChannel dc = DatagramChannel.open();
        dc.socket().bind(new InetSocketAddress(0));
        dc.configureBlocking(false);
        ByteBuffer buf1 = ByteBuffer.allocateDirect(256);
        SocketAddress sa1 = dc.receive(buf1);
        if (sa1 != null)
            ;
        dc.close();
    }
}

