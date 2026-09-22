import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.net.SocketAddress;

public class TplClass6573 {

    private static final void method(java.nio.ByteBuffer rb, java.nio.channels.DatagramChannel dc3, java.net.SocketAddress[] sa) throws Throwable {
        for (int i = 0; i < 3; i++) {
            sa[i] = dc3.receive(rb);
            rb.clear();
        }
    }
}

