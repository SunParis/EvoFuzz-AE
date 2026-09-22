import java.nio.ByteBuffer;
import java.io.IOException;
import java.nio.channels.DatagramChannel;
import java.net.SocketAddress;

public class TplClass6543 {

    private static final void method(java.nio.channels.DatagramChannel dgChannel, java.nio.ByteBuffer data, java.net.SocketAddress sa) throws Throwable {
        try {
            int n = dgChannel.send(data, sa);
        } catch (IOException e) {
        }
    }
}

