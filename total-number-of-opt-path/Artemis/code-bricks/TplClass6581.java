import java.nio.channels.DatagramChannel;
import java.nio.channels.NotYetConnectedException;
import java.nio.ByteBuffer;

public class TplClass6581 {

    private static final void method(java.nio.channels.DatagramChannel dc) throws Throwable {
        try {
            dc.write(ByteBuffer.wrap("another message".getBytes()));
        } catch (NotYetConnectedException expected) {
        }
    }
}

