import java.nio.channels.DatagramChannel;
import java.net.PortUnreachableException;
import java.nio.ByteBuffer;

public class TplClass6539 {

    private static final void method(java.nio.channels.DatagramChannel server, java.nio.ByteBuffer inBuf, java.nio.ByteBuffer outBuf) throws Throwable {
        try {
            server.write(outBuf);
            Thread.sleep(2000);
            inBuf.clear();
            server.read(inBuf);
        } catch (PortUnreachableException pue) {
        }
    }
}

