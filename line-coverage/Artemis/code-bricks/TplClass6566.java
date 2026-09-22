import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.net.PortUnreachableException;

public class TplClass6566 {

    private static final void method(java.nio.ByteBuffer bb, java.nio.channels.DatagramChannel dc1) throws Throwable {
        try {
            dc1.receive(bb);
        } catch (PortUnreachableException pue) {
        }
    }
}

