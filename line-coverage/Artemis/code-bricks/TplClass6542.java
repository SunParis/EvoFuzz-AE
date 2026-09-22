import java.nio.ByteBuffer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.net.SocketAddress;

public class TplClass6542 {

    private static final void method(java.nio.channels.DatagramChannel dgChannel, java.nio.ByteBuffer data, int port, java.lang.String[] targets) throws Throwable {
        for (int i = 0; i < targets.length; i++) {
            data.rewind();
            SocketAddress sa = new InetSocketAddress(targets[i], port);
            try {
                int n = dgChannel.send(data, sa);
            } catch (IOException e) {
            }
        }
    }
}

