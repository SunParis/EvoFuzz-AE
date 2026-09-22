import java.nio.channels.DatagramChannel;
import java.net.PortUnreachableException;
import java.nio.ByteBuffer;

public class TplClass6521 {

    private static final void method(int n, java.nio.channels.DatagramChannel dc) throws Throwable {
        try {
            n = dc.read(ByteBuffer.allocate(100));
        } catch (PortUnreachableException pue) {
            // expected
        }
    }
}

