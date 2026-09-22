import java.nio.ByteBuffer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.net.SocketAddress;

public class TplClass6544 {

    private static final void method(int port, java.lang.String[] targets) throws Throwable {
        ByteBuffer data = ByteBuffer.wrap("TESTING DATA".getBytes());
        DatagramChannel dgChannel = DatagramChannel.open();
        for (int i = 0; i < targets.length; i++) {
            data.rewind();
            SocketAddress sa = new InetSocketAddress(targets[i], port);
            try {
                int n = dgChannel.send(data, sa);
            } catch (IOException e) {
            }
        }
        dgChannel.close();
    }
}

