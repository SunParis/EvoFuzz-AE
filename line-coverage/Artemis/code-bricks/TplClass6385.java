import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TplClass6385 {

    private static final void method(java.nio.ByteBuffer bb, java.nio.channels.SocketChannel server) throws Throwable {
        while (bb.hasRemaining()) {
            if (server.read(bb) < 0)
                ;
        }
    }
}

