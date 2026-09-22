import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.net.StandardSocketOptions;

public class TplClass6423 {

    private static final void method(java.nio.channels.ServerSocketChannel ssc) throws Throwable {
        SocketChannel peer = ssc.accept();
        try {
            peer.setOption(StandardSocketOptions.SO_LINGER, 0);
            peer.configureBlocking(false);
            peer.write(ByteBuffer.wrap(new byte[128 * 1024]));
        } finally {
            peer.close();
        }
    }
}

