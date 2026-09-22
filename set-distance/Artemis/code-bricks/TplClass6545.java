import java.nio.ByteBuffer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.net.SocketAddress;

public class TplClass6545 {

    private static final void method(java.nio.channels.DatagramChannel dgChannel, int i, java.nio.ByteBuffer data, int port, java.lang.String[] targets) throws Throwable {
        data.rewind();
        SocketAddress sa = new InetSocketAddress(targets[i], port);
        try {
            int n = dgChannel.send(data, sa);
        } catch (IOException e) {
        }
    }
}

