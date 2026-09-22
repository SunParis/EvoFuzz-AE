import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

public class TplClass5661 {

    private static final void method(java.nio.channels.AsynchronousSocketChannel peer) throws Throwable {
        peer.write(ByteBuffer.wrap("welcome".getBytes())).get();
        peer.shutdownOutput();
        peer.close();
    }
}

