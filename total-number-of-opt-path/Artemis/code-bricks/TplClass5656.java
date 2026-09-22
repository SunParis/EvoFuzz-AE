import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

public class TplClass5656 {

    private static final void method(java.nio.channels.AsynchronousSocketChannel[] peers) throws Throwable {
        // write data to each of the accepted connections
        for (AsynchronousSocketChannel peer : peers) {
            peer.write(ByteBuffer.wrap("welcome".getBytes())).get();
            peer.shutdownOutput();
            peer.close();
        }
    }
}

