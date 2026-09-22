import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.net.SocketAddress;

public class TplClass6577 {

    private static final void method(java.nio.ByteBuffer rb, int i, java.nio.channels.DatagramChannel dc3, java.net.SocketAddress[] sa) throws Throwable {
        sa[i] = dc3.receive(rb);
        rb.clear();
    }
}

