import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;

public class TplClass6578 {

    private static final void method(java.nio.ByteBuffer bb, java.nio.channels.DatagramChannel dc1) throws Throwable {
        try {
            dc1.receive(bb);
        } catch (ClosedChannelException cce) {
            // Correct result
        }
    }
}

