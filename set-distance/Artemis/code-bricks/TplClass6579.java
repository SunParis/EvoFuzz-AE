import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;

public class TplClass6579 {

    private static final void method() throws Throwable {
        ByteBuffer bb = ByteBuffer.allocate(10);
        DatagramChannel dc1 = DatagramChannel.open();
        dc1.close();
        try {
            dc1.receive(bb);
        } catch (ClosedChannelException cce) {
            // Correct result
        }
    }
}

