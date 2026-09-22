import java.nio.channels.DatagramChannel;
import java.nio.ByteBuffer;

public class TplClass6541 {

    private static final void method(java.nio.channels.DatagramChannel server, java.nio.ByteBuffer inBuf, java.nio.ByteBuffer outBuf) throws Throwable {
        server.write(outBuf);
        Thread.sleep(2000);
        inBuf.clear();
        server.read(inBuf);
    }
}

