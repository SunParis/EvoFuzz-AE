import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.net.StandardSocketOptions;

public class TplClass6424 {

    private static final void method(java.nio.channels.SocketChannel peer) throws Throwable {
        peer.setOption(StandardSocketOptions.SO_LINGER, 0);
        peer.configureBlocking(false);
        peer.write(ByteBuffer.wrap(new byte[128 * 1024]));
    }
}

