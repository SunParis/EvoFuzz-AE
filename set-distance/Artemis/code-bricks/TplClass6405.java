import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TplClass6405 {

    private static final void method(java.nio.channels.SocketChannel server, int STOP) throws Throwable {
        ByteBuffer bb = ByteBuffer.allocate(100);
        try {
            int n = server.read(bb);
            if (n != 1) {
                String msg = (n < 0) ? "Unexpected EOF" : "One byte expected";
            }
            bb.flip();
            if (bb.get() != (byte) STOP)
                ;
            bb.flip();
            server.write(bb);
        } catch (IOException ioe) {
        }
    }
}

